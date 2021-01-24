/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rotp.ui.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import rotp.ui.BasePanel;
import rotp.ui.BaseText;
import rotp.ui.main.SystemPanel;

public class StartOptionsUI extends BasePanel implements MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;
    private static final Color backgroundHaze = new Color(0,0,0,160);
    
    public static final Color hoverC = Color.yellow;
    public static final Color depressedC = new Color(208,160,0);

    public static final Color lightBrown = new Color(178,124,87);
    public static final Color brown = new Color(141,101,76);
    public static final Color darkBrown = new Color(112,85,68);
    public static final Color darkerBrown = new Color(75,55,39);
    
    Rectangle hoverBox;
    Rectangle okBox = new Rectangle();
    BasePanel parent;
    BaseText galaxyAgeText;
    BaseText randomEventsText;
    BaseText starDensityText;
    BaseText researchRateText;
    BaseText techTradingText;
    BaseText warpSpeedText;
    BaseText nebulaeText;
    BaseText councilWinText;
    
    public StartOptionsUI() {
        init0();
    }
    private void init0() {
        setOpaque(false);
        Color textC = SystemPanel.whiteText;
        randomEventsText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        researchRateText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        galaxyAgeText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        techTradingText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        starDensityText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        warpSpeedText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        nebulaeText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        councilWinText = new BaseText(this, false, 20, 20,-78,  textC, textC, hoverC, depressedC, textC, 0, 0, 0);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void init() {
        randomEventsText.displayText(randomEventsStr());
        researchRateText.displayText(researchRateStr());
        techTradingText.displayText(techTradingStr());
        galaxyAgeText.displayText(galaxyAgeStr());
        starDensityText.displayText(starDensityStr());
        warpSpeedText.displayText(warpSpeedStr());
        nebulaeText.displayText(nebulaeStr());
        councilWinText.displayText(councilWinStr());
    }
    public void open(BasePanel p) {
        parent = p;
        init();
        enableGlassPane(this);
    }
    public void close() {
        disableGlassPane();
    }
    @Override
    public void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        
        int w = getWidth();
        int h = getHeight();
        Graphics2D g = (Graphics2D) g0;
        
        
        // draw background "haze"
        g.setColor(backgroundHaze);
        g.fillRect(0, 0, w, h);
        
        int leftM = scaled(300);
        int rightM = s100;
        int topM = s90;
        int w1 = w-leftM-rightM;
        int h1 = h-topM-s90;
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(leftM, topM, w1, h1);
        String title = text("SETTINGS_TITLE");
        g.setFont(narrowFont(30));
        int sw = g.getFontMetrics().stringWidth(title);
        int x1 = leftM+((w1-sw)/2);
        int y1 = topM+s40;
        drawBorderedString(g, title, 1, x1, y1, Color.black, Color.white);
        
        g.setFont(narrowFont(18));
        String expl = text("SETTINGS_DESCRIPTION");
        g.setColor(SystemPanel.blackText);
        g.drawString(expl, leftM+s10, y1+s20);
        
        Stroke prev = g.getStroke();
        g.setStroke(stroke3);

        int y2 = scaled(200);
        int x2 = leftM+s10;
        int w2 = (w1-s40)/2;
        int h2 = s90;
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, galaxyAgeText.stringWidth(g)+s10,s30);
        galaxyAgeText.setScaledXY(x2+s20, y2+s7);
        galaxyAgeText.draw(g);
        String desc = text("SETTINGS_GALAXY_AGE_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        List<String> lines = this.wrappedLines(g,desc, w2-s30);
        int y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }
        
        y2 += (h2+s20);
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, starDensityText.stringWidth(g)+s10,s30);
        starDensityText.setScaledXY(x2+s20, y2+s7);
        starDensityText.draw(g);
        desc = text("SETTINGS_STAR_DENSITY_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }       
       
        y2 += (h2+s20);
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, nebulaeText.stringWidth(g)+s10,s30);
        nebulaeText.setScaledXY(x2+s20, y2+s7);
        nebulaeText.draw(g);
        desc = text("SETTINGS_NEBULAE_DESC");
         g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }
        
        y2 += (h2+s20);
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, randomEventsText.stringWidth(g)+s10,s30);
        randomEventsText.setScaledXY(x2+s20, y2+s7);
        randomEventsText.draw(g);
        desc = text("SETTINGS_RANDOM_EVENTS_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }
        
        // right side
        y2 = scaled(200);
        w2 = (w1-s40)/2;
        h2 = s90;
        x2 = leftM+s10+w2+s20;
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, researchRateText.stringWidth(g)+s10,s30);
        researchRateText.setScaledXY(x2+s20, y2+s7);
        researchRateText.draw(g);
        desc = text("SETTINGS_RESEARCH_RATE_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }
        
        y2 += (h2+s20);
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, warpSpeedText.stringWidth(g)+s10,s30);
        warpSpeedText.setScaledXY(x2+s20, y2+s7);
        warpSpeedText.draw(g);
        desc = text("SETTINGS_WARP_SPEED_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }
          
        y2 += (h2+s20);
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, councilWinText.stringWidth(g)+s30,s30);
        councilWinText.setScaledXY(x2+s20, y2+s7);
        councilWinText.draw(g);
        desc = text("SETTINGS_COUNCIL_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }

        y2 += (h2+s20);
        g.setColor(SystemPanel.blackText);
        g.drawRect(x2, y2, w2, h2);
        g.setPaint(GameUI.settingsSetupBackground(w));
        g.fillRect(x2+s10, y2-s10, techTradingText.stringWidth(g)+s10,s30);
        techTradingText.setScaledXY(x2+s20, y2+s7);
        techTradingText.draw(g);
        desc = text("SETTINGS_TECH_TRADING_DESC");
        g.setColor(SystemPanel.blackText);
        g.setFont(narrowFont(18));
        lines = this.wrappedLines(g,desc, w2-s30);
        y3 = y2+s10;
        for (String line: lines) {
            y3 += s20;
            g.drawString(line, x2+s20, y3);
        }

        g.setStroke(prev);

        // draw settings button
        int cnr = s5;
        int smallButtonH = s30;
        int smallButtonW = scaled(180);
        okBox.setBounds(w-scaled(289), scaled(640), smallButtonW, smallButtonH);
        g.setPaint(GameUI.buttonLeftBackground());
        g.fillRoundRect(okBox.x, okBox.y, smallButtonW, smallButtonH, cnr, cnr);
        g.setFont(narrowFont(20));
        String text6 = text("SETTINGS_ACCEPT");
        int sw6 = g.getFontMetrics().stringWidth(text6);
        int x6 = okBox.x+((okBox.width-sw6)/2);
        int y6 = okBox.y+okBox.height-s8;
        Color c6 = hoverBox == okBox ? Color.yellow : GameUI.borderBrightColor();
        drawShadowedString(g, text6, 2, x6, y6, GameUI.borderDarkColor(), c6);
        prev = g.getStroke();
        g.setStroke(stroke1);
        g.drawRoundRect(okBox.x, okBox.y, okBox.width, okBox.height, cnr, cnr);
        g.setStroke(prev);

    }
    private String randomEventsStr() {
        String opt = text(newGameOptions().selectedRandomEventOption());
        return text("SETTINGS_RANDOM_EVENTS", opt)+"   ";
    }
    private String starDensityStr() {
        String opt = text(newGameOptions().selectedStarDensityOption());
        return text("SETTINGS_STAR_DENSITY", opt)+"   ";
    }
    private String researchRateStr() {
        String opt = text(newGameOptions().selectedResearchRate());
        return text("SETTINGS_RESEARCH_RATE", opt)+"   ";
    }
    private String techTradingStr() {
        String opt = text(newGameOptions().selectedTechTradeOption());
        return text("SETTINGS_TECH_TRADING", opt)+"   ";
    }
    private String galaxyAgeStr() {
        String opt = text(newGameOptions().selectedGalaxyAge());
        return text("SETTINGS_GALAXY_AGE", opt)+"   ";
    }
    private String warpSpeedStr() {
        String opt = text(newGameOptions().selectedWarpSpeedOption());
        return text("SETTINGS_WARP_SPEED", opt)+"   ";
    }
    private String nebulaeStr() {
        String opt = text(newGameOptions().selectedNebulaeOption());
        return text("SETTINGS_NEBULAE", opt)+"   ";
    }
    private String councilWinStr() {
        String opt = text(newGameOptions().selectedCouncilWinOption());
        return text("SETTINGS_COUNCIL_WIN", opt)+"   ";
    }
    private void toggleGalaxyAge() {
        softClick();
        newGameOptions().selectedGalaxyAge(newGameOptions().nextGalaxyAge());
        galaxyAgeText.repaint(galaxyAgeStr());
    //    repaint();
    }
    private void toggleRandomEvents() {
        softClick();
        newGameOptions().selectedRandomEventOption(newGameOptions().nextRandomEventOption());
        randomEventsText.repaint(randomEventsStr());
    }
    private void toggleStarDensity() {
        softClick();
        newGameOptions().selectedStarDensityOption(newGameOptions().nextStarDensityOption());
        starDensityText.repaint(starDensityStr());
    }
    private void toggleResearchRate(MouseEvent e) {
        softClick();
        newGameOptions().selectedResearchRate(newGameOptions().nextResearchRate());
        researchRateText.repaint(researchRateStr());
    }
    private void toggleTechTrading(MouseEvent e) {
        softClick();
        newGameOptions().selectedTechTradeOption(newGameOptions().nextTechTradeOption());
        techTradingText.repaint(techTradingStr());
    }
    private void toggleWarpSpeed(MouseEvent e) {
        softClick();
        newGameOptions().selectedWarpSpeedOption(newGameOptions().nextWarpSpeedOption());
        warpSpeedText.repaint(warpSpeedStr());
    }
    private void toggleNebulae(MouseEvent e) {
        softClick();
        newGameOptions().selectedNebulaeOption(newGameOptions().nextNebulaeOption());
        nebulaeText.repaint(nebulaeStr());
    }
    private void toggleCouncilWin(MouseEvent e) {
        softClick();
        newGameOptions().selectedCouncilWinOption(newGameOptions().nextCouncilWinOption());
        councilWinText.repaint(councilWinStr());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                close();
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:
                parent.advanceHelp();
                break;
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {  }
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Rectangle prevHover = hoverBox;
        hoverBox = null;
        if (randomEventsText.contains(x,y))
            hoverBox = randomEventsText.bounds();
        else if (starDensityText.contains(x,y))
            hoverBox = starDensityText.bounds();
        else if (galaxyAgeText.contains(x,y))
            hoverBox = galaxyAgeText.bounds();
        else if (researchRateText.contains(x,y))
            hoverBox = researchRateText.bounds();
        else if (techTradingText.contains(x,y))
            hoverBox = techTradingText.bounds();
        else if (warpSpeedText.contains(x,y))
            hoverBox = warpSpeedText.bounds();
        else if (nebulaeText.contains(x,y))
            hoverBox = nebulaeText.bounds();
        else if (councilWinText.contains(x,y))
            hoverBox = councilWinText.bounds();
        else if (okBox.contains(x,y))
            hoverBox = okBox;
		
        if (hoverBox != prevHover) {
            if (prevHover == randomEventsText.bounds())
                randomEventsText.mouseExit();
            else if (prevHover == galaxyAgeText.bounds())
                galaxyAgeText.mouseExit();
            else if (prevHover == starDensityText.bounds())
                starDensityText.mouseExit();
            else if (prevHover == researchRateText.bounds())
                researchRateText.mouseExit();
            else if (prevHover == techTradingText.bounds())
                techTradingText.mouseExit();
            else if (prevHover == warpSpeedText.bounds())
                warpSpeedText.mouseExit();
            else if (prevHover == nebulaeText.bounds())
                nebulaeText.mouseExit();
            else if (prevHover == councilWinText.bounds())
                councilWinText.mouseExit();
            if (hoverBox == randomEventsText.bounds())
                randomEventsText.mouseEnter();
            else if (hoverBox == galaxyAgeText.bounds())
                galaxyAgeText.mouseEnter();
            else if (hoverBox == starDensityText.bounds())
                starDensityText.mouseEnter();
            else if (hoverBox == researchRateText.bounds())
                researchRateText.mouseEnter();
            else if (hoverBox == techTradingText.bounds())
                techTradingText.mouseEnter();
            else if (hoverBox == warpSpeedText.bounds())
                warpSpeedText.mouseEnter();
            else if (hoverBox == nebulaeText.bounds())
                nebulaeText.mouseEnter();
            else if (hoverBox == councilWinText.bounds())
                councilWinText.mouseEnter();
            if (prevHover != null)
                repaint(prevHover);
            if (hoverBox != null)
                repaint(hoverBox);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() > 3)
            return;
        if (hoverBox == null)
            return;
        int x = e.getX();
        int y = e.getY();
        if (hoverBox == randomEventsText.bounds())
            toggleRandomEvents();
        else if (hoverBox == galaxyAgeText.bounds())
            toggleGalaxyAge();
        else if (hoverBox == starDensityText.bounds())
            toggleStarDensity();
        else if (hoverBox == researchRateText.bounds())
            toggleResearchRate(e);
        else if (hoverBox == techTradingText.bounds())
            toggleTechTrading(e);
        else if (hoverBox == warpSpeedText.bounds())
            toggleWarpSpeed(e);
        else if (hoverBox == nebulaeText.bounds())
            toggleNebulae(e);
        else if (hoverBox == councilWinText.bounds())
            toggleCouncilWin(e);
        else if (hoverBox == okBox)
            close();
    }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) {
        if (hoverBox != null) {
            hoverBox = null;
            repaint();
        }
    }

}
