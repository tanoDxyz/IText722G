package com.tanodxyz.itext722;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.util.Consumer;

import com.tanodxyz.itext722g.IText722;
import com.tanodxyz.itext722g.io.image.ImageData;
import com.tanodxyz.itext722g.io.image.ImageDataFactory;
import com.tanodxyz.itext722g.kernel.colors.Color;
import com.tanodxyz.itext722g.kernel.colors.DeviceRgb;
import com.tanodxyz.itext722g.kernel.font.PdfFont;
import com.tanodxyz.itext722g.kernel.font.PdfFontFactory;
import com.tanodxyz.itext722g.kernel.geom.PageSize;
import com.tanodxyz.itext722g.kernel.pdf.PdfDocument;
import com.tanodxyz.itext722g.kernel.pdf.PdfDocumentInfo;
import com.tanodxyz.itext722g.kernel.pdf.PdfWriter;
import com.tanodxyz.itext722g.kernel.pdf.canvas.draw.DottedLine;
import com.tanodxyz.itext722g.layout.Document;
import com.tanodxyz.itext722g.layout.element.Image;
import com.tanodxyz.itext722g.layout.element.LineSeparator;
import com.tanodxyz.itext722g.layout.element.Paragraph;
import com.tanodxyz.itext722g.layout.element.Text;
import com.tanodxyz.itext722g.layout.properties.TextAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * Remember this is just a sample and not the best way to code.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private final Handler handler = new Handler();
    private View createPdfButton;
    private View viewPdfButton;
    private TextView logTv;
    private File dest;
    private boolean pdfBeingCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        IText722.init(this);

        dest = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "SimplePdf.pdf");
        createPdfButton = findViewById(R.id.createPdfButton);
        viewPdfButton = findViewById(R.id.viewPdfButton);
        logTv = findViewById(R.id.logTv);


        createPdfButton.setOnClickListener((v) -> {

            if (pdfBeingCreated) {
                logTxtToScreen("Wait Pdf being created!!!!");
            } else {
                hideUnHideButton(true, viewPdfButton);
                pdfBeingCreated = true;
                createPdf((created) -> {
                    if (created) {
                        hideUnHideButton(false, viewPdfButton);
                        viewPdfButton.setOnClickListener(this::viewPdf);
                    } else {
                        viewPdfButton.setOnClickListener((view) -> {
                        });
                        hideUnHideButton(true, viewPdfButton);
                    }
                    pdfBeingCreated = false;
                });
            }
        });
    }


    private void viewPdf(View view) {
        try {
            openFile(this, (dest));
        } catch (Exception e) {
            e.printStackTrace();
            logTxtToScreen("Failed to open file --> " + e.getLocalizedMessage());
        }
    }

    public void createPdf(Consumer<Boolean> listener) {
        new Thread(() -> {
            try {
                logTxtToScreen("Creating Pdf....");
                PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(dest));
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                PdfDocumentInfo info = pdfDocument.getDocumentInfo();
                ImageData imageData = ImageDataFactory.create(new URL("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
                Image image = new Image(imageData);
                info.setTitle("Example of iText7 by Dxyz");
                info.setAuthor("Dxyz");
                info.setSubject("iText7 PDF Demo");
                info.setKeywords("iText, PDF, Dxyz");
                info.setCreator("A simple tutorial example");

                Document document = new Document(pdfDocument, PageSize.A4, true);
                document.add(image);
                /***
                 * Variables for further use....
                 */
                Color mColorAccent = new DeviceRgb(153, 204, 255);
                Color mColorBlack = new DeviceRgb(0, 0, 0);
                float mHeadingFontSize = 20.0f;
                float mValueFontSize = 26.0f;
                /**
                 * How to USE FONT....
                 */
                PdfFont font = PdfFontFactory.createFont("fonts/cavier_dreams.ttf", "UTF-8", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);

                // LINE SEPARATOR
                LineSeparator lineSeparator = new LineSeparator(new DottedLine());
                lineSeparator.setStrokeColor(new DeviceRgb(0, 0, 68));
                // Title Order Details...
                // Adding Title....
                Text mOrderDetailsTitleChunk = new Text("Order Details").setFont(font).setFontSize(36.0f).setFontColor(mColorBlack);
                Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk)
                        .setTextAlignment(TextAlignment.CENTER);
                document.add(mOrderDetailsTitleParagraph);

                // Fields of Order Details...
                // Adding Chunks for Title and value
                Text mOrderIdChunk = new Text("Order No:").setFont(font).setFontSize(mHeadingFontSize).setFontColor(mColorAccent);
                Paragraph mOrderIdParagraph = new Paragraph(mOrderIdChunk);
                document.add(mOrderIdParagraph);

                Text mOrderIdValueChunk = new Text("#123123").setFont(font).setFontSize(mValueFontSize).setFontColor(mColorBlack);
                Paragraph mOrderIdValueParagraph = new Paragraph(mOrderIdValueChunk);
                document.add(mOrderIdValueParagraph);

                // Adding Line Breakable Space....
                document.add(new Paragraph(""));
                // Adding Horizontal Line...
                document.add(lineSeparator);
                // Adding Line Breakable Space....
                document.add(new Paragraph(""));

                // Fields of Order Details...
                Text mOrderDateChunk = new Text("Order Date:").setFont(font).setFontSize(mHeadingFontSize).setFontColor(mColorAccent);
                Paragraph mOrderDateParagraph = new Paragraph(mOrderDateChunk);
                document.add(mOrderDateParagraph);

                Text mOrderDateValueChunk = new Text("06/07/2017").setFont(font).setFontSize(mValueFontSize).setFontColor(mColorBlack);
                Paragraph mOrderDateValueParagraph = new Paragraph(mOrderDateValueChunk);
                document.add(mOrderDateValueParagraph);

                document.add(new Paragraph(""));
                document.add(lineSeparator);
                document.add(new Paragraph(""));

                // Fields of Order Details...
                Text mOrderAcNameChunk = new Text("Account Name:").setFont(font).setFontSize(mHeadingFontSize).setFontColor(mColorAccent);
                Paragraph mOrderAcNameParagraph = new Paragraph(mOrderAcNameChunk);
                document.add(mOrderAcNameParagraph);

                Text mOrderAcNameValueChunk = new Text("Dxyz").setFont(font).setFontSize(mValueFontSize).setFontColor(mColorBlack);
                Paragraph mOrderAcNameValueParagraph = new Paragraph(mOrderAcNameValueChunk);
                document.add(mOrderAcNameValueParagraph);

                document.add(new Paragraph(""));
                document.add(lineSeparator);
                document.add(new Paragraph(""));
                document.close();
                logTxtToScreen("PDF created ....");
                handler.post(() -> listener.accept(true));
                listener.accept(true);
            } catch (Exception e) {
                logTxtToScreen("PDF Failed to create --> " + e.getLocalizedMessage());
                handler.post(() -> listener.accept(false));
            }
        }).start();

    }


    void hideUnHideButton(boolean hide, View... views) {
        handler.post(() -> {
            for (View view : views) {
                if (hide) {
                    view.setVisibility(View.INVISIBLE);
                } else {
                    view.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    void logTxtToScreen(String txt) {
        handler.post(() -> logTv.setText(txt));
    }

    void openFile(Context context, File file) throws ActivityNotFoundException,
            IOException {

        if (file.exists()) {
            Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                    BuildConfig.APPLICATION_ID + ".provider", file);

            String urlString = file.toString().toLowerCase();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            if (urlString.toLowerCase().contains(".pdf")) {
                intent.setDataAndType(uri, "application/pdf");
            } else {
                intent.setDataAndType(uri, "*/*");
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            logTxtToScreen("File open in external viewer failed -> file does not exists");
        }
    }
}