package ddl;

import entity.TestClass;
import entity.TestClass2;
import exception.DDLException;
import org.junit.Assert;
import org.junit.Test;

public class TableCreatorTest {
    @Test(expected = DDLException.class)
    public void testGetTableNameShouldThrowExceptionInTableCreator() {
        TableCreator tableCreator = new TableCreator();
        tableCreator.getTableName(TestClass2.class);
    }

    @Test
    public void testGetTableName () {
        TableCreator tableCreator = new TableCreator();
        String tableName = tableCreator.getTableName(TestClass.class);
        Assert.assertNotNull(tableName);
        Assert.assertEquals("Table Name == Annotation Value", "test", tableName);
    }

    @Test
    public void testGetPK() {
        TableCreator tableCreator = new TableCreator();
        String primaryKey = tableCreator.getPK(Test.class);
        Assert.assertEquals("Primary Key", "id", primaryKey);
    }

}
