import javax.swing.JOptionPane;

public class StatementProcessor {

    InterfaceExecution IE;
    PlotPage plotPage;
    MyFileReader fileReader;
    String Line;
    Boolean isChoice = false; // 是否在选择
    Boolean isFindSelect = false; // 是否找到选项
    Boolean isInSelect = false; // 是否在选项
    Boolean isEnding = false; // 是否在结尾

    char isSelected = ' '; // 当前选项
    int currentLine = 0; // 当前行

    int startLayerCount = 0; // 当前*层

    StatementProcessor(InterfaceExecution interfaceExecution, PlotPage plotPage) {
        IE = interfaceExecution;
        this.plotPage = plotPage;
        this.fileReader = new MyFileReader(MyStyle.getNewChapterFilepath());
    }

    void simpleAnalyze() {

        while (((Line = fileReader.readNonBlankLine()) != null) && !isEnding) {
            currentLine++;
            // 读取所有语句

            if (currentLine < MyDbDate.stories.get(StoryPage.getCurrentStory()).archivedRowCount) {
                // 没到上次存储的行数，则跳过

            } else if (isChoice && !isFindSelect && !(Line.length() == 1 && Line.charAt(0) == isSelected)) {
                // 在选项中，并且没有找到选项，并且当前行不是要的选项，则跳过

            } else if (isChoice && isFindSelect && !isInSelect) {
                // 在选项中，已经找到选项，但是当前段不在用户选的选项中，则跳过

            } else {

                // 如果处在选择语句，则会进行状态判断
                if (isChoice && !isFindSelect && (Line.length() == 1 && Line.charAt(0) == isSelected)) {
                    // 如果还没找到用户选项，且当前选项符合用户选项，则置为找到选项和当前正在选项中

                    isFindSelect = true;
                    isInSelect = true;

                } else if (isChoice && isFindSelect && (Line.length() == 1 && Line.charAt(0) != isSelected)) {
                    // 如果当前正在选项中，并且找到了下一个选项（如果下一行只有一个字符，并且不等于用户选项，则这么认为）
                    // 那么设置当前不在选项中

                    isInSelect = false;
                }

                // 开始正常的语句处理
                if (Line.charAt(0) == '#') {
                    // 处理普通段

                } else if (Line.charAt(0) == '-') {
                    // 处理图片

                } else if (Line.charAt(0) == '~') {
                    if (Line.charAt(1) == '~') {
                        // 处理音效

                    } else {
                        // 处理背景音乐

                    }

                } else if (Line.charAt(0) == '￥') {

                    if (isChoice) {
                        // 退出选择语句，初始化选择状态
                        isChoice = false;
                        isFindSelect = false;
                        isInSelect = false;
                        isSelected = ' ';

                    } else {
                        // 进入选择语句，记录用户选项
                        isChoice = true;
                        isSelected = 'A';

                        // 处理选择语句
                        // 展示选择语段
                    }

                } else if (Line.charAt(0) == '@') {

                    if (Line.charAt(1) == '@') {
                        // 设置变量大小

                    } else {
                        // 添加变量

                    }

                } else if (Line.charAt(0) == '*') {
                    // 处理*语句

                    // 处理*内的语句
                    while ((Line = fileReader.readNonBlankLine()) != null) {

                        // 直到再次读到*退出
                        if (Line.charAt(0) != '*') {
                            break;
                        }

                        // 暂时不处理

                        // if (Line.charAt(1) <= startLayerCount) {
                        // // 当前*层小于等于记录值，则跳过

                        // } else {
                        // // 当前*层大于记录值，记录当前*层数
                        // startLayerCount = Line.charAt(1);

                        // //开始比较是否符合*内语句
                        // }

                        // if(不符合*内语句){

                        // while ((Line = fileReader.readNonBlankLine()) != null){

                        // }

                        // }

                    }

                } else if (Line.equals("end")) {

                    isEnding = true;

                } else {
                    if (Line.contains(String.valueOf('：'))) {
                        String[] parts = Line.split(String.valueOf('：'));

                        if (parts.length == 2) {
                            // 处理对话语句
                            // parts[0] 与 parts[1]

                        } else {
                            System.out.println("格式出错，错误的冒号“：”位置。");
                        }
                    } else {
                        System.out.println("未识别语句：" + Line);
                    }
                }

            }
            MyDbDate.stories.get(StoryPage.getCurrentStory()).archivedRowCount = currentLine;

        }

        // 退出循环，需要存档 archivedRowCount
        // 如果有下一章，则打开下一章
        // 打开下一个文件处理，先判断是否是分支章节，是就通过读取*0语句来判断要进入的章节
        finalizeAndProceed();

    }

    void finalizeAndProceed() {
        String news = "当前“" + MyDbDate.stories.get(StoryPage.getCurrentStory()).storyName
                + "”第" + MyDbDate.stories.get(StoryPage.getCurrentStory()).currentChapter
                + "章结束，正在进入下一章。";
        JOptionPane.showMessageDialog(null, news, "提示", JOptionPane.ERROR_MESSAGE);

        MyDbDate.stories.get(StoryPage.getCurrentStory()).currentChapter++;

        fileReader.close();
        fileReader = new MyFileReader("新的路径");
    }
}
