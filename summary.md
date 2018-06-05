实训总结报告
阶段一
阶段一主要是为实训项目做基本的技术准备，学会使用Vi, JAVA, Ant 和 Junit, 以及熟悉GridWorld的使用，并完成指定的任务。

vi使用
vi分为三种状态，分别是命令模式、插入模式、底行模式，命令模式下可控制屏幕光标的移动，字符或行的删除；插入模式下才可以做文字输入；低行模式下可将文件保存或退出vi，或查找字符串等。
进入vi，在系统提示符号输入vi，后跟文件的名称，即可计入vi编辑该文件，如$vi myfile。
进入vi后，默认是命令模式，若要编辑文字，先按一下[i]进入插入模式，才可以编辑。
若要从插入模式转为命令模式，则按一下[esc]
若要退出vi，即按一下[:]进入底行模式，如:w filename是以filename文件名保存，:wq即保存并退出，:q!即不保存退出
java环境变量配置
首先去官网下载[java开发工具包JDK][1]，选择对应的系统激进型下载
安装完成后，右击“我的电脑”，点击“属性”，选择“高级系统设置”，添加下列参数
变量名：JAVA_HOME

变量值：C:\Program Files (x86)\Java\jdk1.8.0_91 // 要根据自己的实际路径配置

变量名：CLASSPATH

变量值：.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar; //记得前面有个"."

变量名：Path

变量值：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

Ant的使用
Ant是java项目的构造工具，利用它可以方便地编译运行java项目程序，使用方法为在项目的根目录下建立一个build.xml文件，然后在该目录下输入Ant命令，即可构造程序。一个简单的编译运行Helloworld程序的build文件如下所示

    <?xml version="1.0"?>
    <project name="javacTest"default="compile" basedir=".">
            <target name="clean">
                 <delete dir="build"/>
            </target>

            <target name="compile" depends="clean">
                 <mkdir dir="build/classes"/>
            <javac srcdir="src" destdir="build/classes"/>
            </target>
    </project>
junit的使用
junit是一个单元测试工具，这对于分析程序功能和检查错误具有非常打的作用，在eclipse中可以方便地建立junit测试，如下是一个简单的junit测试阳历代码，用来检测程序的输出是否为helloworld

        import static org.junit.Assert.*;
        import org.junit.Test;
        public class HelloWorldTest {
            public HelloWorld helloworld = new HelloWorld();
            @Test
            Public void testHello() {
             helloworld.hello();
             assertEquals(―Hello World!‖, helloworld.getStr());
            }
        }
阶段二
阶段二主要是实现各种基类的拓展类

Part2
实现CircleBug、SpiralBug、ZBug、DancingBug的代码编写

Part3
实现jumper类的编写

Part4
实现ModifiedChameleonCritter、ChameleonKid、RockHound、BlusterCritter、QuickCrab、KingCrab的编写

Part5
实现SparseBoundedGrid、hashBoundedrid等类的编写

这些类基本都是继承了题目给出的基类，比如Part2代码的基类是Bug，而我们要实现的只是在Bug的基础上添加各种不同的功能，比如circleBug功能是运动路线是一个圆形。其它Part也是类似地在原有基类上进行修改增加功能，所以代码的实现并不复杂。具体的代码可以看上传的代码包

阶段三
Imageocessing
这道题是要我们实现一个图片处理器，该处理器能读取和写出图片，还能将图片变为灰度、红、黄、蓝的色彩。要实现这些共呢个，必须搞清楚bmp图片的格式和它的编码方式。

位图头： 保存位图文件的总体信息。
位图信息： 保存位图图像的详细信息。
调色板： 保存所用颜色的定义。
位图数据： 保存一个又一个像素的实际图像。
其中，位图信息中保存了图片的长和宽，而位图数据则是保存了图片的像素值矩阵。在读取这个图片时，我们着重获取这三个信息。然后调用java提供的API创建Image，这样图片就可以显示了，具体代码见代码包。

这道题还要求我们把图片变为灰度图片，这利用到一条公式I = 0.299 * R + 0.587 * G + 0.114 *B，其中R,G,B分别为红、绿、蓝通道的颜色值。在前面的图片信息中位图数据保存的就是一个个RGB像素值，我们每次从位图数据读取3个值，分别为BGR，这样就可以或得灰度值，再利用java提供的API，将灰度值传入，即可将图片转为灰度图片。具体代码见代码包。

MazeBug
利用深度优先算法，实现走迷宫找出口的功能，

深度优先搜索用栈（stack）来实现，整个过程可以想象成一个倒立的树形：

1、把根节点压入栈中。
2、每次从栈中弹出一个元素，搜索所有在它下一级的元素，把这些元素压入栈中。并把这个元素记为它下一级元素的前驱。
3、找到所要找的元素时结束程序。
4、如果遍历整个树还没有找到，结束程序。
具体代码见代码包

nuzzle
利用广度优先算法，实现拼图功能

广度优先搜索使用队列（queue）来实现，整个过程也可以看做一个倒立的树形：

1、把根节点放到队列的末尾。
2、每次从队列的头部取出一个元素，查看这个元素所有的下一级元素，把它们放到队列的末尾。并把这个元素记为它下一级元素的前驱。
3、找到所要找的元素时结束程序。
4、如果遍历整个树还没有找到，结束程序。