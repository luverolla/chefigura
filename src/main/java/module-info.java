module unisa.diem.seproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens unisa.diem.seproject to javafx.fxml;
    opens unisa.diem.seproject.model.extensions to javafx.fxml;
    exports unisa.diem.seproject;
    exports unisa.diem.seproject.model.extensions;
}