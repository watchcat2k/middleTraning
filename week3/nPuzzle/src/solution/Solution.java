package solution;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
    private Vector<JigsawNode> openList;  // open表 ：用以保存已发现但未访问的节点  
    private Vector<JigsawNode> closeList; // close表：用以保存已访问的节点  
	/**
	 * 拼图构造函数
	 */
	public Solution() {
        this.beginJNode = null;
        this.endJNode = null;
        this.currentJNode = null;
        this.solutionPath = null;
        this.openList = new Vector<JigsawNode>();
        this.closeList = new Vector<JigsawNode>();
        this.searchedNodesNum = 0;
	}

	/**
	 * 拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = new JigsawNode(bNode);
        this.solutionPath = null;
        this.openList = new Vector<JigsawNode>();
        this.closeList = new Vector<JigsawNode>();
        this.searchedNodesNum = 0;
	}

	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		boolean isCompleted = false;
        // 将搜索过程写入D://BFSearchDialog.txt  
		PrintWriter pw = null;
		
        String filePath = "BFSearchDialog.txt";  
        try {
        	pw = new PrintWriter(new FileWriter(filePath));  
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("PrintWriter error");
		}
        
        // *************************************  
  
        // Write your code here.  
        beginJNode = bNode;
        endJNode = eNode;
        openList.addElement(beginJNode);   
        currentJNode = beginJNode;  
  
        while(!openList.isEmpty()){  
            if (openList.firstElement().equals(endJNode)) {  
                isCompleted = true;  
                getPath();
                break;  
            }else {  
                closeList.addElement(openList.firstElement());  
                Vector<JigsawNode> findAdjacent = findFollowJNodes(openList.firstElement());  
                for( JigsawNode node : findAdjacent ){  
                    openList.addElement(node);  
                }  
                openList.remove(0);  
                currentJNode = openList.firstElement();  
                closeList.addElement(currentJNode);  
                searchedNodesNum++;  
            }  
        }  
          
  
        // *************************************  
        try {
            this.printResult(pw);  
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("printRsult error");
		}

        pw.close();  
        System.out.println("Record into " + filePath);  
        return isCompleted;  
	}

    /**探索所有与jNode邻接(上、下、左、右)且未曾被访问的节点 
     * @param jNode - 要探索的节点 
     * @return 包含所有与jNode邻接且未曾被访问的节点的Vector<JigsawNode>对象 
     */  
    private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {  
        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();  
        JigsawNode tempJNode;  
        for(int i=0; i<4; i++){  
            tempJNode = new JigsawNode(jNode);  
            if(tempJNode.move(i) && !this.closeList.contains(tempJNode) && !this.openList.contains(tempJNode))  
                followJNodes.addElement(tempJNode);  
        }  
        return followJNodes;  
    }  

	/**
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数  
        int dimension = JigsawNode.getDimension();  
        for(int index =1 ; index<dimension*dimension; index++){  
            if(jNode.getNodesState()[index]+1!=jNode.getNodesState()[index+1])  
                s++;  
        }  
  
        //the distance of wrong node  
        int distance = 0;  
        int geometry = 0;  
        for(int i = 1; i <= dimension*dimension; i++){  
            for(int j = 1; j <= dimension*dimension; j++ ){  
                if(jNode.getNodesState()[i] != 0 && jNode.getNodesState()[i] == endJNode.getNodesState()[j]){  
                    int x1 = (i-1) / 5;  
                    int y1 = (i+4) % 5;  
                    int x2 = (j-1) / 5;  
                    int y2 = (j+4) % 5;  
                    int xManhaton = ( x1 >= x2 ? x1-x2 : x2-x1 );  
                    int yManhaton = ( y1 >= y2 ? y1-y2 : y2-y1 );  
                    distance += ( xManhaton + yManhaton );  
                    geometry += Math.sqrt( xManhaton*xManhaton + yManhaton*yManhaton);  
                    break;  
                }  
            }  
        }  
  
        int aver = (int) (s*1 + distance*2 + geometry*1);  
        jNode.setEstimatedValue(aver);  
	}
}
