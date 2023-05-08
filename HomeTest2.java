
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableUtils {

    /**
     * This function returns the text of a cell in a table based on the value of another cell in the same row.
     * @param table           The table element.
     * @param searchColumn    The index (starting from 0) of the column to search for the searchText.
     * @param searchText      The text to search for in the searchColumn.
     * @param returnColumn    The index (starting from 0) of the column from which to return the text.
     */
    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumn) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > searchColumn && cells.get(searchColumn).getText().equals(searchText)) {
                if (cells.size() > returnColumn) {
                    return cells.get(returnColumn).getText();
                } else {
                    return null;
                }
            }
        }
        return null;
    }
    
}

