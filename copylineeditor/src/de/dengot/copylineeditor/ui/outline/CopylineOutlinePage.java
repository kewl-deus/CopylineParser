package de.dengot.copylineeditor.ui.outline;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import de.dengot.copylineeditor.CopylineEditorPlugin;
import de.dengot.copylineeditor.model.Field;
import de.dengot.copylineeditor.ui.editor.CopylineEditor;
import de.dengot.copylineeditor.ui.wizard.FindFieldByNameWizard;
import de.dengot.copylineeditor.ui.wizard.FindFieldByPositionWizard;

public class CopylineOutlinePage extends ContentOutlinePage
{
	private CopylineEditor sourceEditor;

	private IEditorPart fieldEditor;

	private DrillDownAdapter drillDownAdapter;

	private Action findFieldByNameAction;

	private Action findFieldByPositionAction;

	public CopylineOutlinePage(CopylineEditor editor)
	{
		this.sourceEditor = editor;
	}

	public void createControl(Composite parent)
	{
		super.createControl(parent);
		TreeViewer treeViewer = getTreeViewer();
		drillDownAdapter = new DrillDownAdapter(treeViewer);
		treeViewer.setLabelProvider(new FieldTreeLabelProvider());
		treeViewer.setContentProvider(new FieldTreeContentProvider());
		// treeViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);

		registerListeners();
		makeActions();
		hookContextMenu();
		contributeToActionBars();

		setModel(sourceEditor.getModel());
	}

	public void setModel(Field field)
	{
		getTreeViewer().setInput(field);
	}

	public void dispose()
	{
		super.dispose();
		sourceEditor.outlinePageClosed();
		sourceEditor = null;
	}

	private void hookContextMenu()
	{
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager)
			{
				fillContextMenu(manager);
			}
		});
		TreeViewer viewer = getTreeViewer();
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu("CopylineOutlineMenu", menuMgr, viewer);
	}

	private void contributeToActionBars()
	{
		IActionBars bars = getSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager)
	{
		manager.add(findFieldByNameAction);
		manager.add(findFieldByPositionAction);
	}

	private void fillContextMenu(IMenuManager manager)
	{
		manager.add(findFieldByNameAction);
		manager.add(findFieldByPositionAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager)
	{
		manager.add(findFieldByNameAction);
		manager.add(findFieldByPositionAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void registerListeners()
	{
		getTreeViewer().addSelectionChangedListener(
				new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event)
					{
						if (!(event.getSelection() instanceof IStructuredSelection))
							return;
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						if (selection.size() != 1)
							return;
						Object element = selection.getFirstElement();
						if (!(element instanceof Field))
							return;

						Field field = (Field) element;
						sourceEditor.selectAndReveal(field.getOffset(), 0);
					}
				});
	}

	private void makeActions()
	{
		ImageDescriptor defaultImage = PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_OBJS_INFO_TSK);

		findFieldByNameAction = new Action() {
			public void run()
			{
				findFieldByName();
			}
		};
		findFieldByNameAction.setText("Finde Feld");
		findFieldByNameAction
				.setToolTipText("Sucht das von der Wurzel an n‰chste Feld mit bestimmtem Namen");
		ImageDescriptor findFieldImage = CopylineEditorPlugin.getDefault()
				.getImageDescriptor("icons/taschenlampe_fragezeichen.gif");

		findFieldByNameAction
				.setImageDescriptor(findFieldImage == null ? defaultImage
						: findFieldImage);

		findFieldByPositionAction = new Action() {
			public void run()
			{
				findFieldByPosition();
			}
		};
		findFieldByPositionAction.setText("Gehe zu Position");
		findFieldByPositionAction
				.setToolTipText("Sucht das Feld zu der angegebenen Position");
		ImageDescriptor gotoFieldByPositionImage = CopylineEditorPlugin
				.getDefault().getImageDescriptor(
						"icons/search_results_view.gif");
		findFieldByPositionAction
				.setImageDescriptor(gotoFieldByPositionImage == null ? defaultImage
						: gotoFieldByPositionImage);

	}

	private void showMessage(String message)
	{
		MessageDialog.openInformation(getTreeViewer().getControl().getShell(),
				"CopylineOutline", message);
	}

	private void findFieldByName()
	{
		FindFieldByNameWizard wizard = new FindFieldByNameWizard();
		WizardDialog dialog = new WizardDialog(getTreeViewer().getControl()
				.getShell(), wizard);
		dialog.open();

		if (wizard.hasFieldnameToFind())
		{
			Field gotoField = Field.findByName(sourceEditor.getModel(), wizard
					.getFieldnameToFind().toUpperCase());
			if (gotoField == null)
			{
				showMessage("Das Feld mit der Bezeichnung '"
						+ wizard.getFieldnameToFind()
						+ "' konnte nicht gefunden werden");
			}
			else
			{
				StructuredSelection newSelection = new StructuredSelection(
						gotoField);
				getTreeViewer().setSelection(newSelection);
			}
		}
	}

	private void findFieldByPosition()
	{
		FindFieldByPositionWizard wizard = new FindFieldByPositionWizard();
		WizardDialog dialog = new WizardDialog(getTreeViewer().getControl()
				.getShell(), wizard);
		dialog.open();

		if (wizard.hasPositionToFind())
		{
			int searchPos = wizard.getPositionToFind();
			if (searchPos > sourceEditor.getModel().length())
			{
				showMessage("Die Position " + searchPos
						+ " liegt auﬂerhalb des Suchbereichs");
			}
			Field gotoField = Field.findByPosition(sourceEditor.getModel(),
					searchPos);
			if (gotoField == null)
			{
				showMessage("Das Feld an Position " + searchPos
						+ " konnte nicht gefunden werden");
			}
			else
			{
				StructuredSelection newSelection = new StructuredSelection(
						gotoField);
				getTreeViewer().setSelection(newSelection);
			}
		}
	}

}
