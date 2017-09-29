package view.kz.util.pdf.eBlank;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import view.kz.web.AppController.BundleManager;

import java.util.ArrayList;
import java.util.List;

public class RequestType1BlankGenerator extends ABlankGenerator<RequestType1BlankData> {

    public RequestType1BlankGenerator(RequestType1BlankData data, String locale) {
        super(data, locale);
    }

    @Override
    protected List<Element> getElementList() throws Throwable {
        List<Element> elements = new ArrayList<Element>();
        Paragraph paragraph;

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{6, 3});

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        paragraph = new Paragraph(13, BundleManager.getInterface("blank.courtName", locale) + ":", new Font(getTimesNewRoman(), 12, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(paragraph);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        paragraph = new Paragraph(13, data.getCourt(), new Font(getTimesNewRoman(), 12, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        cell.addElement(paragraph);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        paragraph = new Paragraph(13, BundleManager.getInterface("blank.districtName", locale) + ":", new Font(getTimesNewRoman(), 12, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(paragraph);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        paragraph = new Paragraph(13, data.getDistrict(), new Font(getTimesNewRoman(), 12, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        cell.addElement(paragraph);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        paragraph = new Paragraph(13, BundleManager.getInterface("blank.declarantName", locale) + ":", new Font(getTimesNewRoman(), 12, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(paragraph);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        paragraph = new Paragraph(13, data.getDeclarant(), new Font(getTimesNewRoman(), 12, Font.NORMAL));
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        cell.addElement(paragraph);
        table.addCell(cell);

        elements.add(table);

        paragraph = new Paragraph(BundleManager.getInterface("blank.requestType1.title", locale), new Font(getTimesNewRoman(), 16, Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(15);
        paragraph.setSpacingAfter(15);
        elements.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setSpacingBefore(10);
        paragraph.setFirstLineIndent(20);
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.add(new Chunk(BundleManager.getInterface("blank.requestType1.requestCore", locale) + ": ", new Font(getTimesNewRoman(), 12, Font.BOLD)));
        paragraph.add(new Chunk(data.getCore(), new Font(getTimesNewRoman(), 12, Font.NORMAL)));
        elements.add(paragraph);

        if(data.getFiles().size() > 0) {
            paragraph = new Paragraph();
            paragraph.setSpacingBefore(10);
            paragraph.setFirstLineIndent(20);
            paragraph.add(new Chunk(BundleManager.getInterface("blank.files", locale) + ": ", new Font(getTimesNewRoman(), 12, Font.BOLD)));
            elements.add(paragraph);

            int i = 0;
            for(String s : data.getFiles()) {
                paragraph = new Paragraph();
                paragraph.setFirstLineIndent(30);
                paragraph.add(new Chunk(++i + ". " + s, new Font(getTimesNewRoman(), 12, Font.NORMAL)));
                elements.add(paragraph);
            }
        }
        return elements;
    }
}
