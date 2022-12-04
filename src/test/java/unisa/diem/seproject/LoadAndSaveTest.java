package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;
import java.io.File;

public class LoadAndSaveTest {

    private Project sample;

    @BeforeEach
    public void initProject() {
        CommandManager cm = new CommandManager();
        sample = new Project(cm);
        Sheet sheet = new Sheet(new SheetFormat(20,40), cm);
        sample.addSheet(sheet);
    }

    @Test
    @DisplayName("Test save and load")
    public void testLoadAndSave() {
        Project.save(sample, new File("sample.proj"));
        Project saved = Project.load(new File("sample.proj"));
        assertNotNull(saved);
        Sheet sheet = saved.getSheet();
        assertEquals(20, sheet.getFormat().getWidth());
        assertEquals(40, sheet.getFormat().getHeight());
    }
}
