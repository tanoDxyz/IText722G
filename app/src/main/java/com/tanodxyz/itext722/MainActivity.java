package com.tanodxyz.itext722;

import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.tanodxyz.itext722g.IText722;
import com.tanodxyz.itext722g.kernel.colors.Color;
import com.tanodxyz.itext722g.kernel.geom.LineSegment;
import com.tanodxyz.itext722g.kernel.pdf.PdfDocument;
import com.tanodxyz.itext722g.kernel.pdf.PdfReader;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.EventType;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.data.ClippingPathInfo;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.data.IEventData;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.data.ImageRenderInfo;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.data.PathRenderInfo;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.tanodxyz.itext722g.kernel.pdf.canvas.parser.listener.IEventListener;

import java.io.IOException;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IText722.init(this);

        new Thread(()->{
            try {
                PdfReader pdfReader = new PdfReader(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/ASDF.pdf");
                PdfDocument pdfDocument = new PdfDocument(pdfReader);
                int numberOfPages = pdfDocument.getNumberOfPages();
                for(int i=1;i<=numberOfPages;++i) {
                    System.out.println("Bako: page "+i);
                    PdfDocumentContentParser pdfDocumentContentParser = new PdfDocumentContentParser(pdfDocument);
                    pdfDocumentContentParser.processContent(i, new IEventListener(){
                        @Override
                        public void eventOccurred(IEventData data, EventType type) {
                            String textRenderObj = data instanceof TextRenderInfo? "yes Text " : "";
                            String imageRenderObj = data instanceof ImageRenderInfo ? "yes image " : "";
                            String pathRenderObj = data instanceof PathRenderInfo ? "yes path render info " : "";
                            String clipPathRenderInfo = data instanceof ClippingPathInfo ? "yes clip path render info " : "";
                            System.out.println("Bako: event type " + type + " text,image,path,clipPath "+ (textRenderObj) +" " + imageRenderObj + " "+pathRenderObj + " "+clipPathRenderInfo);


                            if(!textRenderObj.isEmpty()) {
                                TextRenderInfo data1 = (TextRenderInfo) data;
                                float length = data1.getAscentLine().getLength();
                                LineSegment descentLine = data1.getDescentLine();
                                Color fillColor = data1.getFillColor();
                                System.out.println("Bako: fill color "+fillColor.getColorValue() + " "+length + " " + descentLine);
                            }
                        }

                        @Override
                        public Set<EventType> getSupportedEvents() {
                            return null;
                        }
                    });
                }
                pdfReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


}