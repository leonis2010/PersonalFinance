/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.gui.dialog.ArticleAddEditDialog;
import personalfinance.gui.handler.FunctionsHandler;
import personalfinance.gui.table.ArticleTableData;
import personalfinance.gui.toolbar.FunctionsToolBar;
import personalfinance.settings.Style;

/**
 *
 * @author Леонид
 */
public class ArticlePanel extends RightPanel{
    public ArticlePanel (MainFrame frame){
        super(
                frame, 
                new ArticleTableData(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))), 
                "ARTICLES", 
                Style.ICON_PANEL_ARTICLES, 
                new FunctionsToolBar(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))));
    }
}
