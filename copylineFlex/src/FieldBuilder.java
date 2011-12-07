import de.dengot.copylineparser.exception.WrongParentFieldTypeException;
import de.dengot.copylineparser.model.AbstractField;
import de.dengot.copylineparser.model.GroupField;
import de.dengot.copylineparser.model.OccurableField;
import de.dengot.copylineparser.model.SwitchField;
import de.dengot.copylineparser.model.SwitchGroup;
import de.dengot.copylineparser.model.TypedField;

public class FieldBuilder
{

	private static FieldBuilder builder;

	private AbstractField lastBuiltField;

	private int fieldStepNumber;

	private String fieldName;

	private int fieldOccurence;

	private String fieldTypedefinition;

	private String fieldValue;

	private FieldBuilder()
	{
		this.lastBuiltField = new GroupField(1, "COPYLINE-ROOT");
	}

	public static synchronized FieldBuilder getInstance()
	{
		if (builder == null)
		{
			builder = new FieldBuilder();
			builder.clearBuildParameters();
		}
		return builder;
	}

	public void clearBuildParameters()
	{
		fieldStepNumber = 0;

		fieldName = "";

		fieldOccurence = 1;

		fieldTypedefinition = "";

		fieldValue = "";
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public int getFieldOccurence()
	{
		return fieldOccurence;
	}

	public void setFieldOccurence(int fieldOccurence)
	{
		this.fieldOccurence = fieldOccurence;
	}

	public int getFieldStepNumber()
	{
		return fieldStepNumber;
	}

	public void setFieldStepNumber(int fieldStepNumber)
	{
		this.fieldStepNumber = fieldStepNumber;
	}

	public String getFieldTypedefinition()
	{
		return fieldTypedefinition;
	}

	public void setFieldTypedefinition(String fieldTypedefinition)
	{
		this.fieldTypedefinition = fieldTypedefinition;
	}

	public String getFieldValue()
	{
		return fieldValue;
	}

	public void setFieldValue(String fieldValue)
	{
		this.fieldValue = fieldValue;
	}

	public AbstractField getLastBuiltField()
	{
		return lastBuiltField;
	}

	public GroupField buildGroupField()
	{
		GroupField gf = new GroupField(this.fieldStepNumber, this.fieldName,
				this.fieldOccurence);
		this.updateFieldStructure(gf);
		return gf;
	}

	public TypedField buildSimpleField()
	{
		TypedField tf = new TypedField(this.fieldStepNumber, this.fieldName,
				this.fieldOccurence);
		tf.setTypedefinition(this.fieldTypedefinition);
		this.updateFieldStructure(tf);
		return tf;
	}

	public SwitchGroup buildSwitchGroup()
	{
		SwitchGroup swg = new SwitchGroup(this.fieldStepNumber, this.fieldName,
				this.fieldOccurence);
		swg.setTypedefinition(this.fieldTypedefinition);
		this.updateFieldStructure(swg);
		return swg;
	}
	
	public SwitchField buildSwitchField()
	{
		SwitchField swf = new SwitchField(this.fieldName);
		this.updateFieldStructure(swf);
		return swf;
	}

	public AbstractField getRootField()
	{
		AbstractField last = this.getLastBuiltField();
		OccurableField parent = last.isContainer() ? (OccurableField) last
				: last.getParent();

		if (parent == null)
		{
			return last;
		}

		while (parent.getParent() != null)
		{
			parent = parent.getParent();
		}
		return parent;
	}

	private void updateFieldStructure(AbstractField f)
	{
		AbstractField last = this.getLastBuiltField();
		OccurableField current = last.isContainer() ? (OccurableField) last
				: last.getParent();

		while (f.getStepNumber() <= current.getStepNumber())
		{
			current = current.getParent();
		}

		boolean added = false;
		try
		{
			added = ((GroupField) current).add((OccurableField) f);
		}
		catch (ClassCastException cce)
		{
			added = false;
		}

		if (!added)
		{
			try
			{
				added = ((SwitchGroup) current).add((SwitchField) f);
			}
			catch (ClassCastException cce)
			{
				added = false;
			}
		}

		if (added)
		{
			try
			{
				f.setParent(current);
			}
			catch (WrongParentFieldTypeException wpfte)
			{
			}
		}
	}
}
