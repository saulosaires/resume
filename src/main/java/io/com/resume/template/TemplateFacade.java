package io.com.resume.template;

import io.com.resume.profile.ProfileDto;
import io.com.resume.profile.ProfileFacade;
import lombok.RequiredArgsConstructor;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class TemplateFacade {

    private final ProfileFacade profileFacade;
    private final ObjectFactory factory;

    int writableWidthTwips;
    MainDocumentPart mainDocumentPart;

    public void getXML() {

        try {
            File doc = new File("welcome.docx");

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(doc);
            System.out.println(wordMLPackage.getMainDocumentPart().getXML());
        } catch (Docx4JException e) {
            throw new RuntimeException(e);
        }

    }

    public void getTemplate() {

        try {

            ProfileDto profile = profileFacade.findById(1L);

            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            mainDocumentPart = wordPackage.getMainDocumentPart();

            writableWidthTwips = wordPackage.getDocumentModel()
                    .getSections().get(0).getPageDimensions().getWritableWidthTwips();

            addHeader(mainDocumentPart, profile);
            addSummary(mainDocumentPart, profile);

            File exportFile = new File("welcome.docx");
            wordPackage.save(exportFile);

            // System.out.println(XmlUtils.marshaltoString( mainDocumentPart.getXML()));
            System.out.println(mainDocumentPart.getXML());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addHeader(MainDocumentPart mainDocumentPart, ProfileDto profile) {
        mainDocumentPart.getContent().add(getHeaderName(profile));
        mainDocumentPart.getContent().add(getContactsTable(profile));
    }

    private void addSummary(MainDocumentPart mainDocumentPart, ProfileDto profile) {
        mainDocumentPart.getContent().add(factory.createBr());
        mainDocumentPart.getContent().add(factory.createBr());

        mainDocumentPart.getContent().add(createParagraphOfText("summary", 18, true, false, true, JcEnumeration.LEFT));


    }

    private void getummaryHeader(MainDocumentPart mainDocumentPart, ProfileDto profile) {

        //  P p = createParagraphOfText("website", 20, JcEnumeration.LEFT);


    }

    private P getHeaderName(ProfileDto profile) {

        P p = getHeaderNameP();
        R r = getHeaderNameR();

        Text t = factory.createText();
        t.setValue(profile.name());
        r.getContent().add(t);
        p.getContent().add(r);

        return p;
    }

    private Tbl getContactsTable(ProfileDto profile) {

        String email = "Email: " + profile.email();
        String website = profile.website();
        String telephone = "Telephone: " + profile.telephone();
        String instantMessaging = "Instant Messaging: " + profile.instantMessaging();


        Tbl tbl = TblFactory.createTable(2, 2, (writableWidthTwips - 500) / 2);

        P p1 = createParagraphOfText(email, 9, false, false, false, JcEnumeration.LEFT);
        P p2 = createHyperLink(website, 9, JcEnumeration.RIGHT);
        P p3 = createParagraphOfText(telephone, 9, false, false, false, JcEnumeration.LEFT);
        P p4 = createParagraphOfText(instantMessaging, 9, false, false, false, JcEnumeration.RIGHT);


        ((Tc) ((Tr) tbl.getContent().get(0)).getContent().get(0)).getContent().add(p1);
        ((Tc) ((Tr) tbl.getContent().get(0)).getContent().get(1)).getContent().add(p2);
        ((Tc) ((Tr) tbl.getContent().get(1)).getContent().get(0)).getContent().add(p3);
        ((Tc) ((Tr) tbl.getContent().get(1)).getContent().get(1)).getContent().add(p4);


        CTBorder border = new CTBorder();
        border.setVal(STBorder.NONE);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);

        tbl.getTblPr().setTblBorders(borders);
        Jc jc = new Jc();
        jc.setVal(JcEnumeration.CENTER);
        tbl.getTblPr().setJc(jc);

        return tbl;
    }

    private P createHyperLink(String text, int fontSize, JcEnumeration justification) {
        P p = factory.createP();
        R r = factory.createR();
        P.Hyperlink hyperlink = factory.createPHyperlink();

        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        hyperlink.getContent().add(r);

        p.getContent().add(hyperlink);

        RPr rpr = factory.createRPr();
        setFontSize(rpr, fontSize);
        RStyle rStyle = new RStyle();
        rStyle.setVal("Hyperlink");
        rpr.setRStyle(rStyle);

        r.setRPr(rpr);

        PPr paragraphProperties = factory.createPPr();

        setJustification(paragraphProperties, justification);

        p.setPPr(paragraphProperties);

        return p;
    }

    private P createParagraphOfText(String text, int fontSize, boolean bold, boolean italic, boolean caps, JcEnumeration justification) {
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);

        RPr rpr = factory.createRPr();
        setFontSize(rpr, fontSize);

        if (bold)
            rpr.setB(new BooleanDefaultTrue());
        if (italic)
            rpr.setI(new BooleanDefaultTrue());
        if (caps)
            rpr.setCaps(new BooleanDefaultTrue());


        r.setRPr(rpr);

        PPr paragraphProperties = factory.createPPr();

        setJustification(paragraphProperties, justification);

        p.setPPr(paragraphProperties);

        return p;
    }

    private P getHeaderNameP() {

        P p = factory.createP();

        PPr paragraphProperties = factory.createPPr();

        setJustification(paragraphProperties, JcEnumeration.CENTER);

        p.setPPr(paragraphProperties);

        return p;
    }

    private R getHeaderNameR() {
        R r = factory.createR();

        RPr rpr = factory.createRPr();
        setFontSize(rpr, 24);

        BooleanDefaultTrue b = new BooleanDefaultTrue();
        rpr.setB(b);
        rpr.setI(b);
        rpr.setCaps(b);

        r.setRPr(rpr);


        return r;
    }

    private void setFontSize(RPr rpr, int size) {

        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(BigInteger.valueOf((size * 2L)));
        rpr.setSzCs(hpsMeasure);
        rpr.setSz(hpsMeasure);
    }

    private void setJustification(PPr paragraphProperties, JcEnumeration justification) {
        Jc jc = factory.createJc();
        jc.setVal(justification);
        paragraphProperties.setJc(jc);
    }
}
