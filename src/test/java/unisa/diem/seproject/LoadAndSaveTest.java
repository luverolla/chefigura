package unisa.diem.seproject;

import org.junit.jupiter.api.Test;
import unisa.diem.seproject.model.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoadAndSaveTest {

    Project sample;

    private void initProject() {
        CommandManager cm = new CommandManager();
        sample = new Project(cm);
        Sheet sheet = new Sheet(new SheetFormat(20,40), cm);
        sample.addSheet(sheet);
    }

    @Test
    public void testLoadAndSave() {
        initProject();

        Project.save(sample, new File("sample.proj"));
        Project saved = Project.load(new File("sample.proj"));
        assertNotNull(saved);
        Sheet sheet = saved.getSheet();
        assertEquals(sheet.getFormat().getWidth(), 20);
        assertEquals(sheet.getFormat().getHeight(), 40);
    }
}
