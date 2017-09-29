package view.kz.util.pdf.eBlank;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import view.kz.util.pdf.APdfGenerator;
import view.kz.web.AppController.BundleManager;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public abstract class ABlankGenerator<T extends ABlankData> extends APdfGenerator {
    protected static byte[] watermarkImageData;
    protected String locale;
    protected T data;
    private static Image blankLogo;

    static {
        try {
            InputStream is = ABlankGenerator.class.getClassLoader().getResourceAsStream("pen.png");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(baos);
            for(int b; (b = is.read()) != -1; ) {
                out.write(b);
            }
            out.close();
            is.close();
            blankLogo = Image.getInstance(baos.toByteArray());
            blankLogo.scaleAbsolute(70, 70);
        }
        catch(Exception ex) {
            System.err.println("Error init blankLogo");
        }
    }

    protected ABlankGenerator(T data, String locale) {
        this.data = data;
        this.locale = locale;
    }

    private class Template extends PdfPageEventHelper {
        private PdfPTable header;
        private PdfPTable footer;
        private java.util.List<Image> qrImages;

        private PdfPTable getHeader() throws Throwable {
            if(header == null) {
                header = new PdfPTable(1);
                header.setTotalWidth(PageSize.A4.getWidth());
                header.getDefaultCell().setBorderWidthTop(0.5f);
                header.getDefaultCell().setBorderWidthLeft(0);
                header.getDefaultCell().setBorderWidthBottom(0.5f);
                header.getDefaultCell().setBorderWidthRight(0);
                header.getDefaultCell().setPaddingLeft(20);
                header.getDefaultCell().setPaddingRight(20);
                header.getDefaultCell().setPaddingTop(5);
                header.getDefaultCell().setPaddingBottom(5);

                PdfPTable inner = new PdfPTable(3);
                inner.setTotalWidth(new float[]{7, 20, 20});

                PdfPCell cell = blankLogo == null ? new PdfPCell() : new PdfPCell(blankLogo);
                cell.setPaddingTop(5);
                cell.setPaddingBottom(5);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                inner.addCell(cell);

                cell = new PdfPCell();
                cell.setPaddingTop(-2);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                Paragraph innerParagraph = new Paragraph(11, "Қазақстан Руспублликасының электронды құжаты", new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_LEFT);
                cell.addElement(innerParagraph);
                innerParagraph = new Paragraph(11, "Өтініш басқармасы | \"ЭПЦ\" ©", new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_LEFT);
                innerParagraph.setSpacingAfter(5);
                cell.addElement(innerParagraph);
                innerParagraph = new Paragraph(11, "Управление заявлениями | ЭЦП \" ©\"", new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_LEFT);
                cell.addElement(innerParagraph);
                innerParagraph = new Paragraph(11, "Электроный документ Республики Казахстан", new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_LEFT);
                innerParagraph.setSpacingAfter(5);
                cell.addElement(innerParagraph);
                innerParagraph = new Paragraph(11, "Request Control |\" ECR\" ©", new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_LEFT);
                cell.addElement(innerParagraph);
                innerParagraph = new Paragraph(11, "Electronical document for the Republic of Kazakhstan", new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_LEFT);
                cell.addElement(innerParagraph);
                inner.addCell(cell);

                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                Paragraph paragraph = new Paragraph();
                innerParagraph = new Paragraph(20, getNumberLabel() + data.getNumber(), new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_RIGHT);
                paragraph.add(innerParagraph);
                innerParagraph = new Paragraph(20, "ЖСН (ИИН): " + data.getXin(), new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_RIGHT);
                paragraph.add(innerParagraph);
                innerParagraph = new Paragraph(20, getDateLabel() + data.getDate(), new Font(getTimesNewRoman(), 10, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_RIGHT);
                paragraph.add(innerParagraph);
                cell.addElement(paragraph);
                inner.addCell(cell);

                header.addCell(inner);
                header.completeRow();
            }
            return header;
        }

        private java.util.List<Image> getQrImages() throws Throwable {
            if(qrImages == null) {
                qrImages = new ArrayList<Image>();

                String xmlDataStr = data.getQrData();
                int i = 0;
                while(true) {
                    boolean doBreak = false;
                    int endIndex = (i + 1) * FIX_CHAR_COUNT;
                    if(endIndex > xmlDataStr.length()) {
                        endIndex = xmlDataStr.length();
                    }
                    if(endIndex == xmlDataStr.length()) {
                        doBreak = true;
                    }
                    String xmlPartStr = xmlDataStr.substring(i * FIX_CHAR_COUNT, endIndex);
                    while(xmlPartStr.length() < FIX_CHAR_COUNT) {
                        xmlPartStr += " ";
                    }
                    xmlPartStr = "<sud><p>" + (i + 1) + "</p><d>" + xmlPartStr + "</d></sud>";
                    BarcodeQRCode qr = new BarcodeQRCode(xmlPartStr, 100, 100, null);
                    Image qrImage = qr.getImage();
                    qrImage.scaleAbsolute(60, 60);
                    qrImage.setAbsolutePosition(5 + 5 * (i + 1) + 60 * i, 25);
                    qrImages.add(qrImage);
                    if(doBreak) {
                        break;
                    }
                    i++;
                }
            }
            return qrImages;
        }

        private PdfPTable getFooter() throws Throwable {
            if(footer == null) {
                footer = new PdfPTable(1);
                footer.setTotalWidth(PageSize.A4.getWidth());
                footer.getDefaultCell().setBorderWidth(0);
//                footer.getDefaultCell().setBorderWidthTop(0.5f);
//                footer.getDefaultCell().setBorderWidthRight(0);
//                footer.getDefaultCell().setBorderWidthBottom(0);
//                footer.getDefaultCell().setBorderWidthLeft(0);
                footer.getDefaultCell().setPaddingLeft(10);
                footer.getDefaultCell().setPaddingRight(10);
                footer.getDefaultCell().setPaddingTop(70);
                footer.getDefaultCell().setPaddingBottom(2);

                PdfPTable inner = new PdfPTable(1);
                PdfPCell cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                Paragraph paragraph = new Paragraph();
                Paragraph innerParagraph = new Paragraph(6, "Осы құжат «Электрондық құжат және электрондық цифрлық колтаңба туралы» Қазақстан Республикасының 2003 жылғы 7 қаңтардағы N 370-II Заңы 7 бабының 1 тармағына сәйкес қағаз тасығыштағы құжатпен бірдей.", new Font(getTimesNewRoman(), 6, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.add(innerParagraph);
                innerParagraph = new Paragraph(6, "Данный документ согласно пункту 1 статьи 7 ЗРК от 7 января 2003 года «Об электронном документе и электронной цифровой подписи» равнозначен документу на бумажном носителе.", new Font(getTimesNewRoman(), 6, Font.NORMAL));
                innerParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.add(innerParagraph);
                cell.addElement(paragraph);
                inner.addCell(cell);

                footer.addCell(inner);
                footer.completeRow();
            }
            return footer;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte wmCanvas = writer.getDirectContentUnder();
                Image wmImage = Image.getInstance(getWatermarkImageData());
                wmImage.scaleAbsolute(500, 500);
                wmImage.setAbsolutePosition(0, 86);
                wmCanvas.addImage(wmImage);

                PdfContentByte canvas = writer.getDirectContent();
                getHeader().writeSelectedRows(0, -1, 0, document.getPageSize().getHeight() - 10, canvas);
                if(data.getSignerFio() != null) {
                    canvas.beginText();
                    canvas.setFontAndSize(getTimesNewRoman(), 12);
                    canvas.showTextAligned(Element.ALIGN_LEFT, BundleManager.getInterface("blank.signedBy", locale, data.getSignerFio()), 20, 88, 0);
                    canvas.endText();
                    for(Image image : getQrImages()) {
                        canvas.addImage(image);
                    }
                }
                getFooter().writeSelectedRows(0, -1, 0, 100, canvas);
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
        }
    }

    protected String getNumberLabel() {
        return "Құжат нөмірі (номер документа): ";
    }

    protected String getDateLabel() {
        return "Жіберілген күні (Дата отправки): ";
    }

    @Override
    protected byte[] getWatermarkImageData() throws Throwable {
        if(watermarkImageData == null) {
            InputStream is = ABlankGenerator.class.getClassLoader().getResourceAsStream("grep.png");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(baos);
            for (int b; (b = is.read()) != -1; ) {
                out.write(b);
            }
            out.close();
            is.close();
            watermarkImageData = baos.toByteArray();
        }
        return watermarkImageData;
    }

    @Override
    protected float getWatermarkWidth() {
        return 0;
    }

    @Override
    protected float getWatermarkHeight() {
        return 0;
    }

    @Override
    protected float getWatermarkVerticalIndent() {
        return 0;
    }

    public void fillPdfOutputStream(OutputStream outputStream) throws Throwable {
        Document document = getDocument();
        document.setMargins(30, 20, 100, 105);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        writer.setPageEvent(new Template());
        document.open();
        for(Element element : getElementList()) {
            document.add(element);
        }
        document.close();
    }
}
