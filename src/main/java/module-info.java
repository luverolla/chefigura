module unisa.diem.swproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens unisa.diem.seproject to javafx.fxml;
    exports unisa.diem.seproject;
}