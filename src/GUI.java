import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Radek on 2016-05-21.
 */
public class GUI {

    private static JPanel outputPanel = new JPanel(new BorderLayout());
    private static JScrollPane output = new JScrollPane();
    private static Collection<String> outputContent = new ArrayList<>();
    private static JPanel inputPanel;
    private static JScrollPane knowledge;
    private static JList inputList;

    public static void openWindow() {
        JFrame frame = new JFrame();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Silnik Wnioskujący");
        MetalLookAndFeel.setCurrentTheme(new OceanTheme());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        inputPanel = new JPanel(new BorderLayout());
        setInput(inputPanel);
        frame.add(inputPanel, BorderLayout.WEST);

        mainPanel.add(outputPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void print(String mesg) {
        System.out.println("GUI: " + mesg);
        outputContent.add(mesg);
        output = new JScrollPane(new JList(outputContent.toArray()));
        outputPanel.remove(output);
        outputPanel.add(output, BorderLayout.CENTER);
        outputPanel.updateUI();
    }

    public static void clearOutput() {
        outputContent.clear();
        outputPanel.remove(output);
        output = new JScrollPane(new JList(outputContent.toArray()));
        outputPanel.add(output);
        outputPanel.updateUI();
    }

    private static void updateKnowledge() {
        if (SilnikWnioskujacy.getFacts().size() + SilnikWnioskujacy.getRules().size() > 0) {
            Collection coll = new ArrayList<>();
            coll.addAll(SilnikWnioskujacy.getFacts());
            coll.addAll(SilnikWnioskujacy.getRules());
            inputList = new JList(coll.toArray());
            inputPanel.remove(knowledge);
            knowledge = new JScrollPane(inputList);
            inputPanel.add(knowledge);
            inputPanel.updateUI();
        }
    }

    private static void setInput(JPanel input){
        JPanel add = new JPanel(new BorderLayout());
        setAdd(add);
        input.add(add, BorderLayout.NORTH);

        knowledge = new JScrollPane();
        input.add(knowledge, BorderLayout.CENTER);
        updateKnowledge();

        JPanel search = new JPanel(new BorderLayout());
        setSearch(search);
        input.add(search, BorderLayout.SOUTH);
    }

    private static void setAdd(JPanel addPanel) {
        JTextField content = new JTextField();
        addPanel.add(content, BorderLayout.NORTH);

        ButtonGroup typeButtons = new ButtonGroup();
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        addPanel.add(buttonsPanel, BorderLayout.CENTER);
        JRadioButton factType = new JRadioButton("Fakt");
        factType.setSelected(TRUE);
        typeButtons.add(factType);
        buttonsPanel.add(factType);
        factType.setMnemonic(0);
        JRadioButton ruleType = new JRadioButton("Reguła");
        typeButtons.add(ruleType);
        buttonsPanel.add(ruleType);
        ruleType.setMnemonic(1);

        JPanel manageButtonsPanel = new JPanel(new GridLayout(2, 2));
        JButton addThing = new JButton("Dodaj");
        addThing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!content.getText().equals("")) {
                    if (typeButtons.getSelection().getMnemonic() == 0)
                        SilnikWnioskujacy.addFact(new Fact(content.getText()));
                    else
                        SilnikWnioskujacy.addRule(new Rule(content.getText()));
                    updateKnowledge();
                    content.setText("");
                }
            }
        });
        manageButtonsPanel.add(addThing);

        JButton delThing = new JButton("Usuń");
        manageButtonsPanel.add(delThing);
        delThing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SilnikWnioskujacy.deleteThing(inputList.getSelectedIndex());
                updateKnowledge();
            }
        });

        JButton editButton = new JButton("Edytuj");
        manageButtonsPanel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = inputList.getSelectedIndex();
                ArrayList<Fact> facts = new ArrayList<>(SilnikWnioskujacy.getFacts());
                if (index < 0)
                    return;
                if (index < facts.size()) {
                    content.setText(facts.get(index).toString());
                    factType.setSelected(TRUE);
                }
                else {
                    ArrayList<Rule> rules = new ArrayList<>(SilnikWnioskujacy.getRules());
                    index -= facts.size();
                    content.setText(rules.get(index).toString());
                    ruleType.setSelected(TRUE);
                }
            }
        });

        JButton saveButton = new JButton("Zmień");
        manageButtonsPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (content.getText().equals(""))
                    return;
                SilnikWnioskujacy.deleteThing(inputList.getSelectedIndex());
                if (typeButtons.getSelection().getMnemonic() == 0)
                    SilnikWnioskujacy.addFact(new Fact(content.getText()));
                else
                    SilnikWnioskujacy.addRule(new Rule(content.getText()));
                content.setText("");
                updateKnowledge();
            }
        });

        addPanel.add(manageButtonsPanel, BorderLayout.SOUTH);
    }

    private static void setSearch(JPanel search) {
        JPanel wantedPanel = new JPanel(new GridLayout(2, 1));
        search.add(wantedPanel, BorderLayout.NORTH);
        JLabel wantedLabel = new JLabel("Szukana:");
        wantedPanel.add(wantedLabel);
        JTextField wanted = new JTextField(SilnikWnioskujacy.getWanted().toString());
        wantedPanel.add(wanted);

        ButtonGroup buttons = new ButtonGroup();
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        search.add(buttonsPanel, BorderLayout.CENTER);
        JRadioButton backwardButton = new JRadioButton("Wstecz");
        backwardButton.setSelected(TRUE);
        buttons.add(backwardButton);
        buttonsPanel.add(backwardButton);
        backwardButton.setMnemonic(0);
        JRadioButton forwardButton = new JRadioButton("W przód");
        buttons.add(forwardButton);
        buttonsPanel.add(forwardButton);
        forwardButton.setMnemonic(1);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              clearOutput();
                                              if (wanted.getText().equals(""))
                                                  GUI.print("Podaj szukaną.");
                                              else {
                                                  if (buttons.getSelection().getMnemonic() == 0) {
                                                      SilnikWnioskujacy.setMode(Mode.backward);
                                                      GUI.print("WYSZUKIWANIE WSTECZ");
                                                  } else {
                                                      SilnikWnioskujacy.setMode(Mode.forward);
                                                      GUI.print("WYSZUKIWANIE W PRZÓD");
                                                  }
                                                  GUI.print(" ");
                                                  SilnikWnioskujacy.setWanted(new Variable(wanted.getText()));
                                                  Solver.solve(SilnikWnioskujacy.getFacts(),
                                                          SilnikWnioskujacy.getRules(),
                                                          SilnikWnioskujacy.getWanted(),
                                                          SilnikWnioskujacy.getMode());
                                                  updateKnowledge();
                                              }
                                          }
                                      });
        search.add(startButton, BorderLayout.SOUTH);
    }
}
