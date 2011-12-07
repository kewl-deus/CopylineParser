/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package copylineparser.actions;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;

import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;

/**
 * Adds actions to a workbench window.
 */
public final class WorkbenchActionBuilder
{
    private IWorkbenchWindow window;

    /**
     * A convience variable and method so that the actionConfigurer doesn't need
     * to get passed into registerGlobalAction every time it's called.
     */
    private IActionBarConfigurer fActionBarConfigurer;

    // generic actions
    private IWorkbenchAction closeAction;

    private IWorkbenchAction closeAllAction;

    private IWorkbenchAction closeAllSavedAction;

    private IWorkbenchAction saveAction;

    private IWorkbenchAction saveAllAction;

    private IWorkbenchAction saveAsAction;

    // generic retarget actions
    private IWorkbenchAction undoAction;

    private IWorkbenchAction redoAction;

    private IWorkbenchAction cutAction;

    private IWorkbenchAction copyAction;

    private IWorkbenchAction pasteAction;

    private IWorkbenchAction selectAllAction;

    private IWorkbenchAction findAction;

    private IWorkbenchAction revertAction;

    private IWorkbenchAction quitAction;

    /**
     * Constructs a new action builder which contributes actions to the given
     * window.
     * 
     * @param window
     *            the window
     */
    public WorkbenchActionBuilder(IWorkbenchWindow window)
    {
        this.window = window;
    }

    /**
     * Returns the window to which this action builder is contributing.
     */
    private IWorkbenchWindow getWindow()
    {
        return window;
    }

    /**
     * Builds the actions and contributes them to the given window.
     * 
     * @param windowConfigurer
     * @param actionBarConfigurer
     */
    public void makeAndPopulateActions(IWorkbenchConfigurer windowConfigurer,
            IActionBarConfigurer actionBarConfigurer)
    {
        makeActions(windowConfigurer, actionBarConfigurer);
        populateMenuBar(actionBarConfigurer);
        populateCoolBar(actionBarConfigurer);
    }

    /**
     * Fills the coolbar with the workbench actions.
     * 
     * @param configurer
     */
    private void populateCoolBar(IActionBarConfigurer configurer)
    {
        ICoolBarManager cbManager = configurer.getCoolBarManager();

        cbManager.add(new GroupMarker("group.file")); //$NON-NLS-1$
        { // File Group
            IToolBarManager fileToolBar = new ToolBarManager(cbManager
                    .getStyle());
            fileToolBar.add(new Separator(IWorkbenchActionConstants.NEW_GROUP));
            fileToolBar.add(new GroupMarker(
                    IWorkbenchActionConstants.SAVE_GROUP));
            fileToolBar.add(saveAction);

            fileToolBar.add(new Separator(
                    IWorkbenchActionConstants.MB_ADDITIONS));

            // Add to the cool bar manager
            cbManager.add(new ToolBarContributionItem(fileToolBar,
                    IWorkbenchActionConstants.TOOLBAR_FILE));
        }

        cbManager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

        cbManager.add(new GroupMarker(IWorkbenchActionConstants.GROUP_EDITOR));

    }

    /**
     * Fills the menu bar with the workbench actions.
     * 
     * @param configurer
     */
    public void populateMenuBar(IActionBarConfigurer configurer)
    {
        IMenuManager menubar = configurer.getMenuManager();
        menubar.add(createFileMenu());
        menubar.add(createEditMenu());
        menubar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    /**
     * Creates and returns the File menu.
     */
    private MenuManager createFileMenu()
    {
        MenuManager menu = new MenuManager(
                "&Datei", IWorkbenchActionConstants.M_FILE); //$NON-NLS-1$
        menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));

        menu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
        menu.add(closeAction);
        menu.add(closeAllAction);
        // menu.add(closeAllSavedAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
        menu.add(new Separator());
        menu.add(saveAction);
        menu.add(saveAsAction);
        menu.add(saveAllAction);

        menu.add(revertAction);
        menu.add(ContributionItemFactory.REOPEN_EDITORS.create(getWindow()));
        menu.add(new GroupMarker(IWorkbenchActionConstants.MRU));
        menu.add(new Separator());
        menu.add(quitAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
        return menu;
    }

    /**
     * Creates and returns the Edit menu.
     */
    private MenuManager createEditMenu()
    {
        MenuManager menu = new MenuManager(
                "&Bearbeiten", IWorkbenchActionConstants.M_EDIT); //$NON-NLS-1$
        menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));

        menu.add(undoAction);
        menu.add(redoAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));

        menu.add(cutAction);
        menu.add(copyAction);
        menu.add(pasteAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));

        menu.add(selectAllAction);
        menu.add(new Separator());

        menu.add(findAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));

        menu.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));

        menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        return menu;
    }

    /**
     * Disposes any resources and unhooks any listeners that are no longer
     * needed. Called when the window is closed.
     */
    public void dispose()
    {
        closeAction.dispose();
        closeAllAction.dispose();
        closeAllSavedAction.dispose();
        saveAction.dispose();
        saveAllAction.dispose();
        saveAsAction.dispose();
        redoAction.dispose();
        cutAction.dispose();
        copyAction.dispose();
        pasteAction.dispose();
        selectAllAction.dispose();
        findAction.dispose();
        revertAction.dispose();
        quitAction.dispose();

        // null out actions to make leak debugging easier
        closeAction = null;
        closeAllAction = null;
        closeAllSavedAction = null;
        saveAction = null;
        saveAllAction = null;
        saveAsAction = null;
        undoAction = null;
        redoAction = null;
        cutAction = null;
        copyAction = null;
        pasteAction = null;
        selectAllAction = null;
        findAction = null;
        revertAction = null;
        quitAction = null;
    }

    /**
     * Creates actions (and contribution items) for the menu bar, toolbar and
     * status line.
     */
    private void makeActions(IWorkbenchConfigurer workbenchConfigurer,
            IActionBarConfigurer actionBarConfigurer)
    {

        // The actions in jface do not have menu vs. enable, vs. disable vs.
        // color
        // There are actions in here being passed the workbench - problem
        setCurrentActionBarConfigurer(actionBarConfigurer);

        saveAction = ActionFactory.SAVE.create(getWindow());
        registerGlobalAction(saveAction);

        saveAsAction = ActionFactory.SAVE_AS.create(getWindow());
        registerGlobalAction(saveAsAction);

        saveAllAction = ActionFactory.SAVE_ALL.create(getWindow());
        registerGlobalAction(saveAllAction);

        undoAction = ActionFactory.UNDO.create(getWindow());
        registerGlobalAction(undoAction);

        redoAction = ActionFactory.REDO.create(getWindow());
        registerGlobalAction(redoAction);

        cutAction = ActionFactory.CUT.create(getWindow());
        registerGlobalAction(cutAction);

        copyAction = ActionFactory.COPY.create(getWindow());
        registerGlobalAction(copyAction);

        pasteAction = ActionFactory.PASTE.create(getWindow());
        registerGlobalAction(pasteAction);

        selectAllAction = ActionFactory.SELECT_ALL.create(getWindow());
        registerGlobalAction(selectAllAction);

        findAction = ActionFactory.FIND.create(getWindow());
        registerGlobalAction(findAction);

        closeAction = ActionFactory.CLOSE.create(getWindow());
        registerGlobalAction(closeAction);

        closeAllAction = ActionFactory.CLOSE_ALL.create(getWindow());
        registerGlobalAction(closeAllAction);

        closeAllSavedAction = ActionFactory.CLOSE_ALL_SAVED.create(getWindow());
        registerGlobalAction(closeAllSavedAction);

        revertAction = ActionFactory.REVERT.create(getWindow());
        registerGlobalAction(revertAction);

        quitAction = ActionFactory.QUIT.create(getWindow());
        registerGlobalAction(quitAction);
    }

    private void setCurrentActionBarConfigurer(
            IActionBarConfigurer actionBarConfigurer)
    {
        this.fActionBarConfigurer = actionBarConfigurer;
    }

    private void registerGlobalAction(IAction action)
    {
        fActionBarConfigurer.registerGlobalAction(action);
    }
}
