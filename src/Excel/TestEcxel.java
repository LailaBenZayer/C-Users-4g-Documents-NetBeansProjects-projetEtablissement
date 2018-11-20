/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import classe.Employe;
import classe.Etudiant;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 4g
 */
public class TestEcxel {
    
    public static void main(String[] args) {
        
        try {
        File myFile = new File("C:\\Users\\4g\\Downloads\\Formatio\\ARCHIVESBIR.xlsx");
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(1);

        //XSSFRow row = mySheet.getRow(1);
        //XSSFCell cell = row.getCell(2);
        for (int i = 1; i < mySheet.getLastRowNum(); i++) {
            XSSFRow rowx = mySheet.getRow(i);
            //for(int j=1;j<rowx.getLastCellNum();j++) {
            XSSFCell cell2 = rowx.getCell(2);
            if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
                continue;
            } else {
                XSSFCell cell1 = rowx.getCell(1);
                System.out.print(cell1.getStringCellValue() + "\t");
                System.out.print(cell2.getStringCellValue().toString() + "\t");
                XSSFCell cell3 = rowx.getCell(3);
                System.out.print(cell3.getNumericCellValue() + "\t");
                XSSFCell cell4 = rowx.getCell(4);
                System.out.print(cell4.getStringCellValue().toString() + "\t");
                XSSFCell cell5 = rowx.getCell(5);
                System.out.print(cell5.getStringCellValue().toString() + "\t");
                XSSFCell cell6 = rowx.getCell(6);
                System.out.print(cell6.getNumericCellValue() + "\t");
                XSSFCell cell7 = rowx.getCell(7);
                System.out.print(cell7.getNumericCellValue() + "\t");
                XSSFCell cell8 = rowx.getCell(8);
                System.out.print(cell8.getStringCellValue().toString() + "\t");
                XSSFCell cell9 = rowx.getCell(9);
                System.out.print(cell9.getNumericCellValue());
                System.out.println("");
                
               // Etudiant et=new Etudiant
               // Employe e=new Employe("nom", "prenom", "email", "password", Date d=new Date() , Date d=new Date(), "profil", "etablissement");
            }

            //}
        }
              
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
