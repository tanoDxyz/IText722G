package com.tanodxyz.itext722;

import android.os.Environment;

import com.tanodxyz.itext722g.barcodes.Barcode1D;
import com.tanodxyz.itext722g.barcodes.Barcode39;
import com.tanodxyz.itext722g.kernel.colors.ColorConstants;
import com.tanodxyz.itext722g.kernel.exceptions.PdfException;
import com.tanodxyz.itext722g.kernel.pdf.PdfDocument;
import com.tanodxyz.itext722g.kernel.pdf.PdfPage;
import com.tanodxyz.itext722g.kernel.pdf.PdfReader;
import com.tanodxyz.itext722g.kernel.pdf.PdfWriter;
import com.tanodxyz.itext722g.kernel.pdf.canvas.PdfCanvas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Barcode39Test  {

    public static final String sourceFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    public static final String destinationFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();



    public void barcode01Test() throws IOException, PdfException, InterruptedException {
        String filename = "/barcode39_01.pdf";
        PdfDocument document = new PdfDocument(new PdfWriter(destinationFolder + filename));

        PdfPage page = document.addNewPage();
        PdfCanvas canvas = new PdfCanvas(page);

        Barcode1D barcode = new Barcode39(document);
        barcode.setCode("9781935182610");

        barcode.setTextAlignment(Barcode1D.ALIGN_LEFT);
        barcode.placeBarcode(canvas, ColorConstants.BLACK, ColorConstants.BLACK);

        document.close();

    }


    public void barcode02Test() throws IOException, PdfException, InterruptedException {
        String filename = "barcode39_02.pdf";
        PdfDocument document = new PdfDocument(new PdfReader(sourceFolder + "/DocumentWithTrueTypeFont1.pdf"),
                new PdfWriter(destinationFolder + filename));

        PdfCanvas canvas = new PdfCanvas(document.getLastPage());

        Barcode1D barcode = new Barcode39(document);
        barcode.setCode("9781935182610");

        barcode.setTextAlignment(Barcode1D.ALIGN_LEFT);
        barcode.placeBarcode(canvas, ColorConstants.BLACK, ColorConstants.BLACK);

        document.close();

    }

    public void barcode03Test() {
        PdfDocument document = new PdfDocument(new PdfWriter(new ByteArrayOutputStream()));
        Barcode39 barcode = new Barcode39(document);
        try {
            barcode.getBarsCode39("9781935*182610");
        } catch (IllegalArgumentException ignored) {
            ignored.printStackTrace();
        }
    }
}
