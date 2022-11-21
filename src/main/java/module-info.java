module unisa.diem.swproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens unisa.diem.swproject to javafx.fxml;
    exports unisa.diem.swproject;
}