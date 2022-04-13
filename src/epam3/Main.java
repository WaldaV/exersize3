package epam3;

import org.w3c.dom.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class MyForm extends JFrame {
    public MyForm() {
        super("Задание три");

        setBounds(100, 50, 900, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.BOLD, 11);

        Box contents = new Box(BoxLayout.Y_AXIS);
        JMenuBar menuBar = new JMenuBar();

        JMenuItem look = new JMenuItem("Просмотреть");
        look.setFont(font);
        look.addActionListener(e -> {
            contents.removeAll();
            Firearms firearms = new Firearms();
            Document document = null;
            try {
                document = buildDocument();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            assert document != null;
            Node firearm = document.getDocumentElement();
            List<Firearm> firearmList = parseFirearm(firearm);
            firearms.setFirearm(firearmList);

            // конец
            contents.add(new JScrollPane(ShowFirearms(parseFirearm(firearm))));
            setContentPane(contents);
        });
        JMenuItem add = new JMenuItem("Добавить");
        add.setFont(font);
        add.addActionListener(e -> AddFirearm());
        JMenuItem delete = new JMenuItem("Удалить");
        delete.setFont(font);
        delete.addActionListener(e -> DeleteFirearm());
        menuBar.add(look);
        menuBar.add(add);
        menuBar.add(delete);
        setJMenuBar(menuBar);
        setContentPane(contents);
        validate();
        setVisible(true);
    }


    public void DeleteFirearm() {
        JFrame deleteForm = new JFrame("form2");
        JPanel jPanel = new JPanel();
        deleteForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteForm.setBounds(100, 100, 500, 150);
        JLabel labelID = new JLabel("Введите id удаляемого оружия");
        jPanel.add(labelID);
        JTextField textID = new JTextField(20);
        jPanel.add(textID);
        JButton button = new JButton("Удалить");
        button.addActionListener(
                e -> {
                    Document document = null;
                    try {
                        document = buildDocument();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    assert document != null;
                    NodeList nodes = document.getElementsByTagName("Firearm");
                    int k = 0;
                    for (int j = 0; j < nodes.getLength(); j++) {
                        Element firearm = (Element) nodes.item(j);
                        String firearmName = firearm.getAttributes().getNamedItem("id").getNodeValue();
                        if (firearmName.equals(textID.getText())) {
                            k = -1;
                            deleteFirearm(document, textID.getText());
                            document.getDocumentElement().normalize();
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = null;
                            try {
                                transformer = transformerFactory.newTransformer();
                            } catch (TransformerConfigurationException ex) {
                                ex.printStackTrace();
                            }
                            DOMSource source = new DOMSource(document);
                            StreamResult result = new StreamResult(new File("Firearms.xml"));
                            assert transformer != null;
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            try {
                                transformer.transform(source, result);
                            } catch (TransformerException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                    if (k == 0) {
                        JFrame mistake = new JFrame("form3");
                        JPanel mistakePanel = new JPanel();
                        mistake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mistake.setBounds(100, 100, 300, 100);
                        JLabel jLabel = new JLabel("Введено не существующее оружее");
                        mistakePanel.add(jLabel);
                        JButton button1 = new JButton("Ок");
                        button1.addActionListener(
                                e1 -> mistake.setVisible(false)
                        );
                        mistakePanel.add(button1);
                        mistake.add(mistakePanel);
                        mistake.validate();
                        mistake.setVisible(true);
                    }
                    deleteForm.setVisible(false);
                });
        jPanel.add(button);
        deleteForm.add(jPanel);
        deleteForm.validate();
        deleteForm.setVisible(true);
    }

    public static void deleteFirearm(Document doc, String gunID) {
        NodeList nodes = doc.getElementsByTagName("Firearm");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element firearm = (Element) nodes.item(i);
            String firearmName = firearm.getAttributes().getNamedItem("id").getNodeValue();
            if (firearmName.equals(gunID)) {
                firearm.getParentNode().removeChild(firearm);
            }
        }
    }

    public void AddFirearm() {
        JFrame addForm = new JFrame("form2");
        JPanel jPanel = new JPanel();
        addForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addForm.setBounds(100, 100, 500, 500);
        JLabel jLabelID = new JLabel("Введите id");
        jPanel.add(jLabelID);
        JTextField textFieldID = new JTextField(40);
        jPanel.add(textFieldID);
        JLabel jLabelModel = new JLabel("Введите модель");
        jPanel.add(jLabelModel);
        JTextField textFieldModel = new JTextField(35);
        jPanel.add(textFieldModel);
        JLabel jLabelHandy = new JLabel("Введите вид испол.");
        jPanel.add(jLabelHandy);
        JTextField textFieldHandy = new JTextField(39);
        jPanel.add(textFieldHandy);
        JLabel jLabelOrigin = new JLabel("Введите страну производства");
        jPanel.add(jLabelOrigin);
        JTextField textFieldOrigin = new JTextField(36);
        jPanel.add(textFieldOrigin);
        JLabel jLabelRange = new JLabel("Введите дальнобойность");
        jPanel.add(jLabelRange);
        JTextField textFieldRange = new JTextField(32);
        jPanel.add(textFieldRange);
        JLabel jLabelEffectiveRange = new JLabel("Введите прицельную дальность");
        jPanel.add(jLabelEffectiveRange);
        JTextField TextFieldEffectiveRange = new JTextField(33);
        jPanel.add(TextFieldEffectiveRange);
        JLabel jLabelClip = new JLabel("Наличие обоймы");
        jPanel.add(jLabelClip);
        JTextField textFieldClip = new JTextField(35);
        jPanel.add(textFieldClip);
        JLabel jLabelOptics = new JLabel("Наличие оптики");
        jPanel.add(jLabelOptics);
        JTextField textFieldOptics = new JTextField(39);
        jPanel.add(textFieldOptics);
        JLabel jLabelMaterial = new JLabel("Введите материал изготовления");
        jPanel.add(jLabelMaterial);
        JTextField textFieldMaterial = new JTextField(33);
        jPanel.add(textFieldMaterial);
        JButton button = new JButton("Добавить");
        button.addActionListener(
                e -> {
                    Document document = null;
                    try {
                        document = buildDocument();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    assert document != null;
                    Element nList = document.getDocumentElement();

                    Element newFirearm = document.createElement("Firearm");
                    newFirearm.setAttribute("id", textFieldID.getText());

                    Element FirearmModel = document.createElement("Model");
                    FirearmModel.appendChild(document.createTextNode(textFieldModel.getText()));
                    newFirearm.appendChild(FirearmModel);

                    Element FirearmHandy = document.createElement("Handy");
                    FirearmHandy.appendChild(document.createTextNode(textFieldHandy.getText()));
                    newFirearm.appendChild(FirearmHandy);

                    Element FirearmOrigin = document.createElement("Origin");
                    FirearmOrigin.appendChild(document.createTextNode(textFieldOrigin.getText()));
                    newFirearm.appendChild(FirearmOrigin);

                    Element FirearmTTC = document.createElement("TTC");
                    newFirearm.appendChild(FirearmTTC);

                    Element TTCRange = document.createElement("Range");
                    TTCRange.appendChild(document.createTextNode(textFieldOrigin.getText()));
                    FirearmTTC.appendChild(TTCRange);

                    Element FirearmEffectiveRange = document.createElement("EffectiveRange");
                    FirearmEffectiveRange.appendChild(document.createTextNode(textFieldRange.getText()));
                    FirearmTTC.appendChild(FirearmEffectiveRange);

                    Element FirearmClip = document.createElement("clip");
                    FirearmClip.appendChild(document.createTextNode(textFieldClip.getText()));
                    FirearmTTC.appendChild(FirearmClip);

                    Element FirearmOptics = document.createElement("Optics");
                    FirearmOptics.appendChild(document.createTextNode(textFieldOptics.getText()));
                    FirearmTTC.appendChild(FirearmOptics);

                    Element FirearmMaterial = document.createElement("Material");
                    FirearmMaterial.appendChild(document.createTextNode(textFieldMaterial.getText()));
                    newFirearm.appendChild(FirearmMaterial);
                    nList.appendChild(newFirearm);

                    Transformer transformer = null;
                    try {
                        transformer = TransformerFactory.newInstance().newTransformer();
                    } catch (TransformerConfigurationException exception) {
                        exception.printStackTrace();
                    }
                    assert transformer != null;
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    StreamResult result = new StreamResult(new File("Firearms.xml"));
                    DOMSource source = new DOMSource(document);
                    try {
                        transformer.transform(source, result);
                    } catch (TransformerException exception) {
                        exception.printStackTrace();
                    }
                    JFrame mistake = new JFrame("form3");
                    JPanel panel1 = new JPanel();
                    mistake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mistake.setBounds(100, 100, 250, 100);
                    JLabel id3 = new JLabel("Введены некорректные данные");
                    panel1.add(id3);
                    JButton button1 = new JButton("Ок");
                    button1.addActionListener(
                            e1 -> mistake.setVisible(false)
                    );
                    panel1.add(button1);
                    mistake.add(panel1);
                    mistake.validate();
                    mistake.setVisible(true);
                    addForm.setVisible(false);
                }
        );
        jPanel.add(button);
        addForm.add(jPanel);
        addForm.validate();
        addForm.setVisible(true);
    }

    public JTable ShowFirearms(List<Firearm> firearmList) {
        String[] columnNames = {"id", "Model", "Handy", "Origin", "Range", "EffectiveRange", "Clip", "Optics", "Material"};
        String[][] column = new String[firearmList.size()][9];
        int i = 0;
        for (Firearm firearm : firearmList) {
            column[i][0] = String.valueOf(firearm.id);
            column[i][1] = firearm.model;
            column[i][2] = firearm.handy;
            column[i][3] = firearm.origin;
            column[i][4] = firearm.range;
            column[i][5] = String.valueOf(firearm.effectiveRange);
            column[i][6] = String.valueOf(firearm.clip);
            column[i][7] = String.valueOf(firearm.optics);
            column[i][8] = firearm.material;
            i++;
        }
        return new JTable(column, columnNames);
    }

    //препод
    private static Document buildDocument() throws Exception {
        File file = new File("Firearms.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }

    private static List<Firearm> parseFirearm(Node firearm) {
        List<Firearm> firearmList = new ArrayList<>();
        NodeList firearmsChild = firearm.getChildNodes();
        for (int i = 0; i < firearmsChild.getLength(); i++) {
            if (firearmsChild.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;
            if (!firearmsChild.item(i).getNodeName().equals("Firearm")) {
                continue;
            }
            int id;
            String model = "";
            String handy = "";
            String origin = "";
            String range = "";
            int effectiveRange = 0;
            boolean clip = true;
            boolean optics = true;
            String material = "";
            id = Integer.parseInt(firearmsChild.item(i).getAttributes().getNamedItem("id").getNodeValue());
            NodeList firearmChild = firearmsChild.item(i).getChildNodes();
            for (int j = 0; j < firearmChild.getLength(); j++) {
                if (firearmChild.item(j).getNodeType() != Node.ELEMENT_NODE) continue;
                switch (firearmChild.item(j).getNodeName()) {
                    case "Model": {
                        model = firearmChild.item(j).getTextContent();
                    }
                    case "Handy": {
                        handy = firearmChild.item(j).getTextContent();
                        break;
                    }
                    case "Origin": {
                        origin = firearmChild.item(j).getTextContent();
                        break;
                    }
                    case "TTC": {
                        NodeList TTCChild = firearmChild.item(j).getChildNodes();
                        for (int k = 0; k < TTCChild.getLength(); k++) {
                            if (TTCChild.item(k).getNodeType() != Node.ELEMENT_NODE) continue;
                            switch (TTCChild.item(k).getNodeName()) {
                                case "Range" -> range = TTCChild.item(k).getTextContent();
                                case "EffectiveRange" -> effectiveRange = Integer.parseInt(TTCChild.item(k).getTextContent());
                                case "Clip" -> clip = Boolean.parseBoolean(TTCChild.item(k).getTextContent());
                                case "Optics" -> optics = Boolean.parseBoolean(TTCChild.item(k).getTextContent());
                                default -> throw new IllegalStateException("Unexpected value: " + TTCChild.item(k).getNodeName());
                            }
                        }
                    }
                    case "Material": {
                        material = firearmChild.item(j).getTextContent();
                        break;
                    }
                }
            }
            Firearm gun = new Firearm(id, model, handy, origin, range, effectiveRange, clip, optics, material);
            firearmList.add(gun);
        }
        return firearmList;
    }

}

public abstract class Main {
    public static void main(String[] args) {
        new MyForm();
    }
}
