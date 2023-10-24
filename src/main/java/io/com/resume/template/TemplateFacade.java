package io.com.resume.template;

import io.com.resume.education.EducationDto;
import io.com.resume.experience.ExperienceDto;
import io.com.resume.profile.ProfileDto;
import io.com.resume.profile.ProfileFacade;
import io.com.resume.skill.dto.SkillDto;
import lombok.RequiredArgsConstructor;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.RStyle;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateFacade {
    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM/yyyy");

    private final ProfileFacade profileFacade;
    private final ObjectFactory factory;
    int writableWidthTwips;
    WordprocessingMLPackage wordPackage;

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

            wordPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

            writableWidthTwips = wordPackage.getDocumentModel()
                    .getSections().get(0).getPageDimensions().getWritableWidthTwips();

            addHeader(mainDocumentPart, profile);
            addBr(mainDocumentPart);
            addSummary(mainDocumentPart, profile);
            addBr(mainDocumentPart);
            addEducation(mainDocumentPart, profile);
            addProfessionalExperience(mainDocumentPart, profile);

            File exportFile = new File("welcome.docx");
            wordPackage.save(exportFile);

            //System.out.println(XmlUtils.marshaltoString(mainDocumentPart.getXML()));
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

        mainDocumentPart.getContent().add(createParagraphOfText("summary", 18, false, true, false, true, JcEnumeration.LEFT));

        P summary = createParagraphOfText(profile.summary(), 11, true, false, false, false, JcEnumeration.BOTH);

        mainDocumentPart.getContent().add(summary);
    }

    private void addEducation(MainDocumentPart mainDocumentPart, ProfileDto profile) {
        mainDocumentPart.getContent().add(createParagraphOfText("education", 18, false, true, false, true, JcEnumeration.LEFT));


        for (EducationDto educationDto : profile.education()) {
            P p = factory.createP();

            String period = ", " + pattern.format(educationDto.startDate()) + " - " + pattern.format(educationDto.endDate());

            R r_degree = getRun(educationDto.degree(), 11, true, false, false);
            R r_at = getRun(" at ", 11, false, false, false);
            R r_school = getRun(educationDto.school(), 11, false, false, false);
            R r_city = getRun(", " + educationDto.city(), 11, false, false, false);
            R r_period = getRun(period, 11, false, false, false);

            p.getContent().add(r_degree);
            p.getContent().add(r_at);
            p.getContent().add(r_school);
            p.getContent().add(r_city);
            p.getContent().add(r_period);

            mainDocumentPart.getContent().add(p);
            addBr(mainDocumentPart);
        }

    }

    private void addProfessionalExperience(MainDocumentPart mainDocumentPart, ProfileDto profile) {

        mainDocumentPart.getContent().add(createParagraphOfText("Professional Experience", 18, false, true, false, true, JcEnumeration.LEFT));

        for (ExperienceDto experienceDto : profile.experiences()) {

            P companyName = getProfessionalExperienceCompanyName(experienceDto);

            mainDocumentPart.getContent().add(companyName);

            var desc = getProfessionalExperienceDescription2(experienceDto);

            mainDocumentPart.getContent().addAll(desc);
            addBr(mainDocumentPart);
            P p_skill = getP(JcEnumeration.LEFT);
            R r_skill = getRun("Skills: ", 11, true, false, false);
            R r_skill_desc = getRun(getSkillStr(experienceDto.skills()), 11, false, false, false);

            p_skill.getContent().add(r_skill);
            p_skill.getContent().add(r_skill_desc);

            mainDocumentPart.getContent().add(p_skill);
            addBr(mainDocumentPart);

        }

    }

    private P getProfessionalExperienceCompanyName(ExperienceDto experienceDto) {

        R tabs = factory.createR();
        tabs.getContent().add(factory.createRTab());

        P p = getP(JcEnumeration.LEFT);

        R r_company = getRun(experienceDto.companyName(), 12, true, false, false);
        R r_country = getRun(" - " + experienceDto.country().iso(), 11, false, false, false);

        p.getContent().add(tabs);
        p.getContent().add(r_company);
        p.getContent().add(r_country);
        p.getContent().add(factory.createBr());

        R r_title = getRun(experienceDto.title(), 11, true, false, false);
        R r_period = getRun(" (" + pattern.format(experienceDto.startDate()) + " - " + pattern.format(experienceDto.endDate()) + ")", 11, false, false, false);

        p.getContent().add(tabs);
        p.getContent().add(r_title);
        p.getContent().add(r_period);

        return p;
    }

    private List<Object> getProfessionalExperienceDescription2(ExperienceDto experienceDto) {

        try {
            String htmlComment = "<span style=\"font-family: Calibri;font-size: 1em;text-align: justify\"><tbody>" +
                    experienceDto.description() + "</tbody></span>";

            XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordPackage);
            //XHTMLImporter.setDivHandler(new DivToSdt());

            return XHTMLImporter.convert(htmlComment, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String getSkillStr(List<SkillDto> skills) {

        return skills.stream().map(SkillDto::name).collect(Collectors.joining(","));

    }

    private P getP(JcEnumeration justification) {
        P p = factory.createP();
        p.setPPr(factory.createPPr());
        setJustification(p.getPPr(), justification);
        return p;
    }

    private R getRun(String text, int fontSize, boolean bold, boolean italic, boolean caps) {

        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        t.setSpace("preserve");
        r.getContent().add(t);
        RPr rpr = factory.createRPr();
        setFontSize(rpr, fontSize);

        if (bold)
            rpr.setB(new BooleanDefaultTrue());
        if (italic)
            rpr.setI(new BooleanDefaultTrue());
        if (caps)
            rpr.setCaps(new BooleanDefaultTrue());

        r.setRPr(rpr);

        return r;
    }

    private void addBr(MainDocumentPart mainDocumentPart) {
        mainDocumentPart.getContent().add(factory.createBr());
        mainDocumentPart.getContent().add(factory.createBr());
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

        P p1 = createParagraphOfText(email, 9, false, false, false, false, JcEnumeration.LEFT);
        P p2 = createHyperLink(website, 9, JcEnumeration.RIGHT);
        P p3 = createParagraphOfText(telephone, 9, false, false, false, false, JcEnumeration.LEFT);
        P p4 = createParagraphOfText(instantMessaging, 9, false, false, false, false, JcEnumeration.RIGHT);


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

    private P createParagraphOfText(String text, int fontSize, boolean tab, boolean bold, boolean italic, boolean caps, JcEnumeration justification) {
        P p = getP(justification);

        R r = getRun(text, fontSize, bold, italic, caps);

        if (tab) {
            R tabs = factory.createR();
            tabs.getContent().add(factory.createRTab());
            p.getContent().add(tabs);
        }
        p.getContent().add(r);

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
