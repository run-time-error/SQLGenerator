package ddl;

import entity.TestClass;
import entity.TestClass2;
import exception.DDLException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

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
    public void testGetPK() throws NoSuchFieldException {
        TableCreator tableCreator = new TableCreator();
        Field primaryKey = tableCreator.getPrimaryKeyField(Test.class);
        Assert.assertEquals("Primary Key Field", Test.class.getField("a"), primaryKey);
    }

}
