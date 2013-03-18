/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package procmusic;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfugue.Player;
import java.lang.Math;

/**
 *
 * @author Eric Dobbs
 */


public class ProcMusic extends javax.swing.JFrame {

    /**
     * Creates new form ProcMusicUI
     */
    public ProcMusic() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSlider1.setMaximum(300);
        jSlider1.setMinimum(30);
        jSlider1.setValue(120);
        jSlider1.setName("Tempo"); // NOI18N

        jLabel1.setText("Tempo");

        jButton1.setText("Play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setLabel("Loop");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    class playThread implements Runnable {
        // playThread first creates a timerThread, then generates a phrase
        // of music and plays it. timerThread should finish at the same time
        // as playThread
        String song;
        playThread( String str) {
            song = "T" + jSlider1.getValue() + " " + str;
        }
        @Override
        public void run() {
            timerThread tt = new timerThread(0);
            Thread newThread = new Thread(tt);
            newThread.start();
            Player threadPlayer = new Player();
            threadPlayer.play(song);
            System.out.println("Thread done!");
        }
    }
    
    class timerThread implements Runnable {
        // timerThread sits around and waits for a preset amount of time, 
        // then creates a new playThread. Ideally, timerThread should finish
        // at the same time as the previous playThread, creating a constant
        // stream of music. This has yet to be nailed down
        int timer;
        timerThread(int i) {
            timer = i;
        }
        @Override
        public void run() {
            System.out.println("Starting timerThread");
            try {
                Thread.sleep((int)(60000.0/jSlider1.getValue() * 16));
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcMusic.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Timer done!");
            
            if (jCheckBox1.isSelected()) {
                System.out.println("Box checked! creating next thread.");
                jTextArea1.setText("");
                playThread mt = new playThread(getPhrase());
                Thread newThread = new Thread(mt);
                newThread.start();
            }
        }
    }
    
    int[] getScale(String chord) {
        // Given a string representing a chord ("C", "Em", "F#"), getScale
        // returns the set of 8 notes making up the framework that can
        // used for creating a melody within that chord
        
        int rt = 60; // Root is middle-C, which is note 60
        String chordNat = String.valueOf(chord.charAt(0));
        String notes = "C D EF G A B";
        String chordCircle = "FCGDAEB";     // Order of keys for sharps/flats
        rt += notes.indexOf( chordNat );
        if (chord.contains("b")) 
            rt -= 1; // Flat keys are one half-step lower
        else if (chord.contains("#"))
            rt += 1; // Sharp keys are one half-step higher
        int scale[] = {rt, rt+2, rt+4, rt+5, rt+7, rt+9, rt+11, rt+12};
        // Initialize the scale based on a standard major scale
        if (chord.contains("m")) 
            scale[2] = scale[2]-1;
            //Minor key? Lower the 3rd by 1/2 step
        if (chordCircle.indexOf(chordNat)<1 | chord.contains("b"))
            scale[3] = scale[3]+1;
            //Flat key? For some reason that means raise the 4th by 1/2
        if (chordCircle.indexOf(chordNat)>1 & !chord.contains("b"))
            scale[6] = scale[6]-1;
            //Sharp key? Lower the 7th by 1/2
        if (chordCircle.indexOf(chordNat)>3 & !chord.contains("b"))
            scale[5] = scale[5]-1;
            //Really sharp key? Lower the 6th by 1/2
        return scale;
    }
    
    String getPhrase() {
        // getPhrase creates a phrase of music based on a randomly
        // chosen chord progression
        int randNum;
        int root = 60;
        int base;
        String song = "";
        String progression[][] = {
            {"C","F","G","C"},
            {"C","F","C","G"},
            {"C","D","F","C"},
            {"C","Am","F","G"},
            {"C","G","Am","F"},
            {"C","Bb","F","G"},
            {"C","Bb","Ab","G"},
            {"C","Bb","Ab","Bb"},
            {"C","Bb","C","Bb"}
        };
        randNum = randNum(0,progression.length - 1);
        System.out.println("Creating phrase with the following progression:");
        for(int i=0; i<progression[randNum].length; i++) {
            jTextArea1.setText(jTextArea1.getText() + progression[randNum][i] + "\t");
            song += randMeasure(getScale(progression[randNum][i]));
        }
        return song;
    }
    
    String randMeasure(int[] scale){
        String song; 
        String rhythms[] = {
            "12345-678---5-3-|", 
            "1-3-5---3---543-|",
            "1-3-2-4-3-5-3-2-|",
            "1---3---5---8---|",
            "1-1-3-3-5-4-3-2-|",
            "8-7-8-----6---5-|",
            "5-----4-3-------|",
            "5-----43--------|",
            "R-123-123-------|",
            "323-6-5------R--|",
            "5---4-3---4-5-1-|",
            "3-3-3-45--4-3-1-|",
            "3-3-3-45--------|"
        };
        int randNum = randNum(0,rhythms.length - 1);
        jTextArea1.setText(jTextArea1.getText() + rhythms[randNum] + "\n");
        song = "V0 ";
        song += quickNote(scale, rhythms[randNum]);
        return song;
    }
    
    String quickNote(int[] scale, String melody){
        String song = "";
        String lengthCounter = new String(melody);
        int melodyLength = lengthCounter.replaceAll("[b#|]","").length();
        int measureCount = lengthCounter.replaceAll("[^|]","").length();
        double length = 0; 
        double duration = 1.0 * measureCount / melodyLength;
        int note = 0;
        for (int i = 0; i < melody.length(); i++){
            char c = melody.charAt(i);
            if(Character.isDigit(c) | c == 'R'){
                if (i != 0)
                    if (note >= 0)
                        song += " [" + (note) + "]/" + length;
                    else 
                        song += " R/" + String.valueOf(length);
                length = duration;
                if (c == 'R')
                    note = -1;
                else
                    note = scale[Character.getNumericValue(c)-1];
            }
            if(c == 'R')
                note = -1;
            if(c == 'b')
                note -= 1;
            if(c == '#')
                note += 1;
            if(c == '-')
                length += duration;
            if(c == '|')
                if (note >= 0)
                    song += " [" + (note) + "]/" + length;
                else 
                    song += " R/" + length;
        }
        System.out.println(song);
        String lower = " V1 [" + (scale[0]-12) + "]w+[" + (scale[2]-12)
                + "]w+[" + (scale[4]-12) + "]w ";
        song += lower;
        
        return song;
    }
    
    int randNum(int min, int max){
        return (int)(Math.random() * (max-min + 1));
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Player myPlayer = new Player();
        jTextArea1.setText("");
        playThread mt = new playThread(getPhrase());
        Thread newThread = new Thread(mt);
        newThread.start();        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private class Music {
        private Player myPlayer = new Player();
        public Player getPlayer() {
            return this.myPlayer;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProcMusic().setVisible(true);
            }
        });
        
        
        

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
