/*
 * PreviewPanel.java
 *
 * Created on August 7, 2007, 11:54 AM
 *
 * Copyright 2007 Mateusz Wenus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.code2uml.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sourceforge.code2uml.controller.Controller;
import net.sourceforge.code2uml.controller.ControllerFactory;
import net.sourceforge.code2uml.graph.BasicNodeComponent;
import net.sourceforge.code2uml.graph.EdgeComponent;
import net.sourceforge.code2uml.graph.Graph;
import net.sourceforge.code2uml.graph.NodeComponent;
import net.sourceforge.code2uml.util.TextSection;

/**
 * The last tabbed pane from user interface. Displays the diagram, allows
 * dragging its nodes and saving the diagram to a file - either as a graph
 * for further modification or as an image. Must be added to a JTabbedPane
 * to work correctly.
 *
 * @author Mateusz Wenus
 */
public class PreviewPanel extends javax.swing.JPanel implements Observer {
    
    private final int graphPadding = 50;
    private Controller controller = ControllerFactory.getInstance();
    private Graph graph;
    
    /**
     * Creates new form PreviewPanel.
     */
    public PreviewPanel() {
        initComponents();
        controller.addObserver(this);
        setBGThreadWorking(false);
    }
    
    /** 
     * Enables and disable graphical components on this panel when the
     * background thread is or is not working.
     *
     * @param b true if and only if the background thread is working
     */
    private void setBGThreadWorking(final boolean b) {
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                saveProgressBar.setVisible(b);
                previousButton.setEnabled(!b);
                saveImageButton.setEnabled(!b);
                graphPanel.setEnabled(!b);
                for(Component comp : graphPanel.getComponents()) {
                    comp.setEnabled(!b);
                }
            }
        });
    }
    
    /**
     * Adds graph's nodes and edges to a proper panel.
     *
     * @param g graph to add
     */
    private void setGraph(final Graph g) {
        graph = g;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                graphPanel.removeAll();
               
                for(NodeComponent node : g.getNodes()) {
                	BasicNodeComponent basicNodeComponent = (BasicNodeComponent) node;
                	System.out.println(node.getUnitName() + " : ");
                	 
	            	 for(TextSection textSection : basicNodeComponent.getSection()) {
	            		 System.out.println("{");
	            		 for(String text : textSection.getTextSectionStrings()) {
	            			 System.out.println(text);
	            		 }
	            		 System.out.println("}");
	            	 }
                	 
                    for(EdgeComponent edge : node.getOutEdges()) {
                        graphPanel.add(edge);
                    }
                    graphPanel.add(node);
                }

                graphPanel.setPreferredSize(null);
                graphPanel.setPreferredSize(new Dimension(graph.getWidth() + graphPadding, graph.getHeight() + graphPadding));
                
                JScrollBar vsb = graphScrollPane.getVerticalScrollBar();
                JScrollBar hsb = graphScrollPane.getHorizontalScrollBar();
                int maxIncrement = 100;
                int horizontalIncrement = Math.max(maxIncrement, (hsb.getMaximum() - hsb.getMinimum()) / 75);
                int verticalIncrement = Math.max(maxIncrement, (vsb.getMaximum() - vsb.getMinimum()) / 75);
                
                graphScrollPane.getVerticalScrollBar().setUnitIncrement(verticalIncrement);
                graphScrollPane.getHorizontalScrollBar().setUnitIncrement(horizontalIncrement);
                graphPanel.revalidate();
            }
        });
    }
    
    /**
     * Sets this panel as active tabbed pane.
     */
    private void setAsSelected() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JTabbedPane parent = (JTabbedPane) getParent();
                parent.setSelectedComponent(self());
            }
        });
    }
    
    /**
     * Helper method for inner class.
     *
     * @return this PreviewPanel
     */
    private PreviewPanel self() {
        return this;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        previousButton = new javax.swing.JButton();
        graphScrollPane = new javax.swing.JScrollPane();
        graphPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        saveImageButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        saveProgressBar = new javax.swing.JProgressBar();

        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        graphScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        graphScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );
        graphScrollPane.setViewportView(graphPanel);

        jLabel1.setText("Drag nodes to desired positions and save the image.");

        saveImageButton.setText("Save Image");
        saveImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageButtonActionPerformed(evt);
            }
        });

        saveProgressBar.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveProgressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveProgressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(graphScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(previousButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveImageButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {previousButton, saveImageButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(previousButton)
                        .addComponent(saveImageButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel1, previousButton, saveImageButton});

    }// </editor-fold>//GEN-END:initComponents
    
    private void saveImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImageButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        
        // there should be only one extension per filter
        chooser.setFileFilter(new FileNameExtensionFilter("bmp file", "bmp"));
        
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        int result = chooser.showSaveDialog(this);
        
        if(result == JFileChooser.APPROVE_OPTION) {
            
            FileNameExtensionFilter filter = (FileNameExtensionFilter) chooser.getFileFilter();
            String suffix = "." + filter.getExtensions()[0];
            String filePath = chooser.getSelectedFile().getAbsolutePath();
            if(!filePath.endsWith(suffix))
                filePath += suffix;
            
            setBGThreadWorking(true);
            controller.saveImage(graphPanel, filePath, saveProgressBar);
        }
    }//GEN-LAST:event_saveImageButtonActionPerformed
    
    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        JTabbedPane parent = (JTabbedPane) getParent();
        parent.setSelectedIndex(parent.getSelectedIndex() - 1);
    }//GEN-LAST:event_previousButtonActionPerformed
    
    /**
     * Retrieves graph representing UML diagram from the controller when it
     * it ready.
     * @param o observable whose state has changed
     * @param arg notification argument (the graph)
     */
    public void update(Observable o, Object arg) {
        if(o == controller && arg instanceof Object[]) {
            Object[] tab = (Object[]) arg;
            int n = (Integer) tab[0];
            if(n == Controller.GRAPH_RESULT) {
                setGraph((Graph) tab[1]);
                setAsSelected();
            }
        } else if(o == controller && arg instanceof Integer) {
            if((Integer) arg == Controller.SAVE_RESULT) {
                setBGThreadWorking(false);
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel graphPanel;
    private javax.swing.JScrollPane graphScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton saveImageButton;
    private javax.swing.JProgressBar saveProgressBar;
    // End of variables declaration//GEN-END:variables
    
}
