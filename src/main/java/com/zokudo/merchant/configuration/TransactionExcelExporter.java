package com.zokudo.merchant.configuration;


import com.zokudo.merchant.model.Transaction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TransactionExcelExporter {
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;
    private List<Transaction> transactionList;

    public TransactionExcelExporter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        xssfWorkbook = new XSSFWorkbook();
        xssfSheet = xssfWorkbook.createSheet("Transaction-Report");
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
    }

    private void writeHeaderRow() {
        Row row = xssfSheet.createRow(0);

        CellStyle style = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setBold(true);
        //font.setFontHeight(12);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("CREATED AT");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("UPDATED AT");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("MERCHANT NAME");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("MERCHANT ID");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("PROGRAM ID");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("CLIENT ID");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("CUSTOMER HASH ID");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("WALLETHASHID");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("AMOUNT");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("PROXY CARD NO");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("TRANSACTION REF NO");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("RETRIEVAL REF NO");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("TRANSACTION TYPE");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("COMMENTS");
        cell.setCellStyle(style);

        cell = row.createCell(14);
        cell.setCellValue("TRANSACTION AMOUNT");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("BILLING AMOUNT");
        cell.setCellStyle(style);

        cell = row.createCell(16);
        cell.setCellValue("CASHBACK AMOUNT");
        cell.setCellStyle(style);

        cell = row.createCell(17);
        cell.setCellValue("CURRENT BALANCE");
        cell.setCellStyle(style);

        cell = row.createCell(18);
        cell.setCellValue("STATUS");
        cell.setCellStyle(style);

    }

    private void writeDataRows() {
        int rowCount = 1;
        for (Transaction transaction : transactionList) {
            Row row = xssfSheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(transaction.getCreatedAt());
            xssfSheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(transaction.getUpdatedAt());
            xssfSheet.autoSizeColumn(1);

            cell = row.createCell(2);
            cell.setCellValue(transaction.getMerchantName());
            xssfSheet.autoSizeColumn(2);

            cell = row.createCell(3);
            cell.setCellValue(transaction.getMerchantId());
            xssfSheet.autoSizeColumn(3);

            cell = row.createCell(4);
            cell.setCellValue(transaction.getProgramId());
            xssfSheet.autoSizeColumn(4);

            cell = row.createCell(5);
            cell.setCellValue(transaction.getClientId());
            xssfSheet.autoSizeColumn(5);

            cell = row.createCell(6);
            cell.setCellValue(transaction.getCustomerHashId());
            xssfSheet.autoSizeColumn(6);

            cell = row.createCell(7);
            cell.setCellValue(transaction.getWalletWashId());
            xssfSheet.autoSizeColumn(7);

            cell = row.createCell(8);
            cell.setCellValue(transaction.getAmount());
            xssfSheet.autoSizeColumn(8);

            cell = row.createCell(9);
            cell.setCellValue(transaction.getProxyCardNo());
            xssfSheet.autoSizeColumn(9);

            cell = row.createCell(10);
            cell.setCellValue(transaction.getTransactionRefNo());
            xssfSheet.autoSizeColumn(10);

            cell = row.createCell(11);
            cell.setCellValue(transaction.getRetrievalRefNo());
            xssfSheet.autoSizeColumn(11);

            cell = row.createCell(12);
            cell.setCellValue(transaction.getTransactionType());
            xssfSheet.autoSizeColumn(12);

            cell = row.createCell(13);
            cell.setCellValue(transaction.getComments());
            xssfSheet.autoSizeColumn(13);

            cell = row.createCell(14);
            cell.setCellValue(transaction.getTransactionAmount());
            xssfSheet.autoSizeColumn(14);

            cell = row.createCell(15);
            cell.setCellValue(transaction.getBillingBmount());
            xssfSheet.autoSizeColumn(15);

            cell = row.createCell(16);
            cell.setCellValue(transaction.getCashbackBmount());
            xssfSheet.autoSizeColumn(16);

            cell = row.createCell(17);
            cell.setCellValue(transaction.getCurrentBalance());
            xssfSheet.autoSizeColumn(17);

            cell = row.createCell(18);
            cell.setCellValue(transaction.getStatus());
            xssfSheet.autoSizeColumn(18);
        }
    }
}
