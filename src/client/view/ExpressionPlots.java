package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import client.constants.ClientDataConstants;
import client.controller.PlotController;
import client.model.ClientDataSingleton;

/**
 * The ExpressionPlots class plots the graph of expressions received from
 * server.
 * 
 * @author Team06
 * @version 1.0
 */
public class ExpressionPlots extends JPanel {

	JPanel blinkPanel = new JPanel();
	JPanel clenchPanel = new JPanel();
	JPanel furrowBrowPanel = new JPanel();
	JPanel laughPanel = new JPanel();
	JPanel lookLeftPanel = new JPanel();
	JPanel lookRightPanel = new JPanel();
	JPanel raiseBrowPanel = new JPanel();
	JPanel smilePanel = new JPanel();
	JPanel smirkLeftPanel = new JPanel();
	JPanel smirkRightPanel = new JPanel();
	JPanel winkLeftPanel = new JPanel();
	JPanel winkRightPanel = new JPanel();
	JSplitPane blinkSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane clenchSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane furrowBrowSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane laughSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane lookLeftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane lookRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane raiseBrowSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane smileSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane smirkLeftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane smirkRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane winkLeftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane winkRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	private PlotController plotController = new PlotController();
	private ArrayList<ArrayList<Float>> plotData = new ArrayList<>();
	private ArrayList<ArrayList<Float>> inputData = new ArrayList<>();
	private ArrayList<Color> colors = new ArrayList<>();
	private GraphPlot graphPlot;
	private GraphPlot blinkGraphPlot;
	private GraphPlot clenchGraphPlot;
	private GraphPlot furrowBrowGraphPlot;
	private GraphPlot laughGraphPlot;
	private GraphPlot lookLeftGraphPlot;
	private GraphPlot lookRightGraphPlot;
	private GraphPlot raiseBrowGraphPlot;
	private GraphPlot smileGraphPlot;
	private GraphPlot smirkLeftGraphPlot;
	private GraphPlot smirkRightGraphPlot;
	private GraphPlot winkLeftGraphPlot;
	private GraphPlot winkRightGraphPlot;

	public ExpressionPlots() {
		this.setPreferredSize(new Dimension(500, 500));
		addPanels();
	}

	/**
	 * setRandomColors method assigns color values
	 * 
	 * @param size
	 *            int size of color array
	 */
	private void setRandomColors(int size) {
		for (int i = 0; i < size; i++) {
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r, g, b);
			colors.add(randomColor);
		}
	}

	/**
	 * addPanels method configures the 12 graph plots on Expressive panel
	 */
	public void addPanels() {
		this.setLayout(new GridLayout(12, 1, 1, 1));
		setPanelsLayout(blinkPanel, blinkSplitPane, ClientDataConstants.BLINK);
		setPanelsLayout(clenchPanel, clenchSplitPane, ClientDataConstants.CLENCH);
		setPanelsLayout(furrowBrowPanel, furrowBrowSplitPane, ClientDataConstants.FURROW_BROW);
		setPanelsLayout(laughPanel, laughSplitPane, ClientDataConstants.LAUGH);
		setPanelsLayout(lookLeftPanel, lookLeftSplitPane, ClientDataConstants.LOOK_LEFT);
		setPanelsLayout(lookRightPanel, lookRightSplitPane, ClientDataConstants.LOOK_RIGHT);
		setPanelsLayout(raiseBrowPanel, raiseBrowSplitPane, ClientDataConstants.RAISE_BROW);
		setPanelsLayout(smilePanel, smileSplitPane, ClientDataConstants.SMILE);
		setPanelsLayout(smirkLeftPanel, smirkLeftSplitPane, ClientDataConstants.SMIRK_LEFT);
		setPanelsLayout(smirkRightPanel, smirkRightSplitPane, ClientDataConstants.SMIRK_RIGHT);
		setPanelsLayout(winkLeftPanel, winkLeftSplitPane, ClientDataConstants.WINK_LEFT);
		setPanelsLayout(winkRightPanel, winkRightSplitPane, ClientDataConstants.WINK_RIGHT);
		this.add(blinkPanel);
		this.add(clenchPanel);
		this.add(furrowBrowPanel);
		this.add(laughPanel);
		this.add(lookLeftPanel);
		this.add(lookRightPanel);
		this.add(raiseBrowPanel);
		this.add(smilePanel);
		this.add(smirkLeftPanel);
		this.add(smirkRightPanel);
		this.add(winkLeftPanel);
		this.add(winkRightPanel);
		setRandomColors(12);
	}

	private void setPanelsLayout(JPanel expression, JSplitPane splitPane, String feature) {
		expression.setLayout(new GridLayout(1, 1, 1, 1));
		splitPane.setResizeWeight(0.1);
		splitPane.setDividerLocation(100);
		splitPane.setEnabled(false);
		splitPane.setDividerSize(0);
		splitPane.add(new JLabel(feature));
		expression.add(splitPane);
	}

	/**
	 * plotGraphForFeature plots feature values received from server
	 * 
	 * @param data
	 *            ArrayList received from server
	 * @return GraphPlot instance of graph
	 */
	private GraphPlot plotGraphForFeature(ArrayList<Float> data) {
		inputData.clear();
		inputData.add(data);
		graphPlot = new GraphPlot(inputData, colors);
		graphPlot.setBackground(Color.LIGHT_GRAY);
		return graphPlot;
	}

	/**
	 * plotExpressionGraph method adds values to the graph and plots graph
	 */
	public void plotExpressionGraph() {
		if (ClientDataSingleton.getInstance().getFaceData() != null) {
			plotData = plotController.getExpressivePlotData();
			blinkGraphPlot = plotGraphForFeature(plotData.get(0));
			addGraphsToPanel(blinkPanel, blinkSplitPane, blinkGraphPlot, ClientDataConstants.BLINK);
			clenchGraphPlot = plotGraphForFeature(plotData.get(1));
			addGraphsToPanel(clenchPanel, clenchSplitPane, clenchGraphPlot, ClientDataConstants.CLENCH);
			furrowBrowGraphPlot = plotGraphForFeature(plotData.get(2));
			addGraphsToPanel(furrowBrowPanel, furrowBrowSplitPane, furrowBrowGraphPlot, ClientDataConstants.FURROW_BROW);
			laughGraphPlot = plotGraphForFeature(plotData.get(3));
			addGraphsToPanel(laughPanel, laughSplitPane, laughGraphPlot, ClientDataConstants.LAUGH);
			lookLeftGraphPlot = plotGraphForFeature(plotData.get(4));
			addGraphsToPanel(lookLeftPanel, lookLeftSplitPane, lookLeftGraphPlot, ClientDataConstants.LOOK_LEFT);
			lookRightGraphPlot = plotGraphForFeature(plotData.get(5));
			addGraphsToPanel(lookRightPanel, lookRightSplitPane, lookRightGraphPlot, ClientDataConstants.LOOK_RIGHT);
			raiseBrowGraphPlot = plotGraphForFeature(plotData.get(6));
			addGraphsToPanel(raiseBrowPanel, raiseBrowSplitPane, raiseBrowGraphPlot, ClientDataConstants.RAISE_BROW);
			smileGraphPlot = plotGraphForFeature(plotData.get(7));
			addGraphsToPanel(smilePanel, smileSplitPane, smileGraphPlot, ClientDataConstants.SMILE);
			smirkLeftGraphPlot = plotGraphForFeature(plotData.get(8));
			addGraphsToPanel(smirkLeftPanel, smirkLeftSplitPane, smirkLeftGraphPlot, ClientDataConstants.SMIRK_LEFT);
			smirkRightGraphPlot = plotGraphForFeature(plotData.get(9));
			addGraphsToPanel(smirkRightPanel, smirkRightSplitPane, smirkRightGraphPlot, ClientDataConstants.SMIRK_RIGHT);
			winkLeftGraphPlot = plotGraphForFeature(plotData.get(10));
			addGraphsToPanel(winkLeftPanel, winkLeftSplitPane, winkLeftGraphPlot, ClientDataConstants.WINK_LEFT);
			winkRightGraphPlot = plotGraphForFeature(plotData.get(11));
			addGraphsToPanel(winkRightPanel, winkRightSplitPane, winkRightGraphPlot, ClientDataConstants.WINK_RIGHT);
		}
	}

	/**
	 * addGraphsToPanel adds plotted graph to the panel
	 * 
	 * @param expression
	 *            Panel object of each expression
	 * @param splitPane
	 *            SplitPane object to split label and graph
	 * @param graphPlotCurr
	 *            GraphPlot graph with values
	 * @param feature
	 *            String with feature name related to expression
	 */
	private void addGraphsToPanel(JPanel expression, JSplitPane splitPane, GraphPlot graphPlotCurr, String feature) {
		expression.removeAll();
		splitPane.removeAll();
		setPanelsLayout(expression, splitPane, feature);
		splitPane.add(graphPlotCurr);
		expression.add(splitPane);
		expression.repaint();
		expression.revalidate();
	}
}