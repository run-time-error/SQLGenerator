package ddl;

import annotations.Column;
import entity.TestClass;
import entity.TestClass2;
import exception.DDLException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

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
    public void testGetAllColumns() throws NoSuchFieldException {
        TableCreator tableCreator = new TableCreator();
        Map <Field, Column> map = tableCreator.getAllColumns(TestClass.class);
        Assert.assertNotNull(map);
        Assert.assertEquals("Map Size: ", map.size(), 1);
        Column column = map.getOrDefault(TestClass.class.getDeclaredField("a"), null);
        Assert.assertNotNull(column);
        Assert.assertEquals("Map Entry: ", TestClass.class.getDeclaredField("a").getDeclaredAnnotation(Column.class), column);

        Map<Field, Column> map2 = tableCreator.getAllColumns(TestClass2.class);
        Assert.assertNotNull(map2);
        Assert.assertEquals("Map Size: ", map2.size(), 0);
    }

    @Test
    public void testCreateTableSQL () {
        TableCreator tableCreator = new TableCreator();
        String sql = tableCreator.getCreateTableSQL(Test.class);
        Assert.assertNotNull("Create Table SQL: ", sql);
    }

}
