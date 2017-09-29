package view.kz.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.io.StreamUtil;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public abstract class APdfGenerator {
    protected static final int FIX_CHAR_COUNT = 1400;
    private static BaseFont timesNewRoman;

    protected abstract List<Element> getElementList() throws Throwable;

    protected static BaseFont getTimesNewRoman() throws IOException, DocumentException {
        if(timesNewRoman == null) {
            InputStream is = APdfGenerator.class.getClassLoader().getResourceAsStream("fonts/times.ttf");
            byte[] rBytes = StreamUtil.inputStreamToArray(is);
            timesNewRoman = BaseFont.createFont("times.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, false, rBytes, null);
//            String path = "C:/Windows/Fonts/times.ttf";
//            timesNewRoman = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, false, null, null);
        }
        return timesNewRoman;
    }

    protected abstract byte[] getWatermarkImageData() throws Throwable;

    protected abstract float getWatermarkWidth();

    protected abstract float getWatermarkHeight();

    protected abstract float getWatermarkVerticalIndent();

    protected Document getDocument() {
        return new Document(PageSize.A4, Utilities.millimetersToPoints(10f), Utilities.millimetersToPoints(10f), Utilities.millimetersToPoints(10f), Utilities.millimetersToPoints(10f));
    }

    private class Watermark extends PdfPageEventHelper {
        private Phrase watermark = null;

        private Phrase getWatermark() throws Throwable {
            if(watermark == null) {
                watermark = new Phrase("WATERMARK", new Font(getTimesNewRoman(), 60, Font.NORMAL, BaseColor.LIGHT_GRAY));
            }
            return watermark;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte canvas = writer.getDirectContentUnder();
                Image image = Image.getInstance(getWatermarkImageData());
//                float newSize = (float)(image.getWidth() / writer.getPageSize().getWidth());
                image.scaleAbsolute(getWatermarkWidth(), getWatermarkHeight());
                image.setAbsolutePosition((writer.getPageSize().getWidth() - getWatermarkWidth()) / 2, writer.getPageSize().getHeight() - getWatermarkHeight() + getWatermarkVerticalIndent());
//                image.setAbsolutePosition(50 , 50);
//                image.setDpi(34, 34);
                canvas.addImage(image);
//                int y=-100;
//                int incr=0;
//                for (int h = -100; h <= 795; h += 170) {
//                    for (int x =- 100; x <= 695; x += 150) {
//                        y = (int)(0.2 * (x + incr)) + h;
//                        image.setAbsolutePosition((x + incr), y);
//                        canvas.addImage(image);
//                    }
//                    incr+=30;
//                }
//                canvas.addImage();
//                ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, getWatermark(), 298, 421, 45);
            }
            catch(Throwable t) {
                if(!t.getClass().getSimpleName().equals("ClientAbortException")) {
                    t.printStackTrace();
                }
            }
        }
    }

    public void fillPdfOutputStream(OutputStream outputStream) throws Throwable {
        Document document = getDocument();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        writer.setPageEvent(new Watermark());
        document.open();
        for(Element element : getElementList()) {
            document.add(element);
        }
        document.close();
    }
}
