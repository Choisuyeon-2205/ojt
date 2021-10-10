import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

/*
        << 개정 이력 ( Modification Infomation ) >>
        수정일             수정자             작업 내용/ 수정내용
       --------         -----------          ---------------------
       2021.10.07         최수연              레이아웃 설계, 기능 구현,
       2021.10.08         최수연

 */
public class ManageSystem extends JFrame {      // JFrame 클래스는 최상위 컨테이너 역할의 클래스이다.
    public static final String String = null;

    // 내부 클래스 객체 생성 => 내부 클래스 각 참조 변수
    MenuMain menuMain = new MenuMain();
    West west = new West();
    Buttons buttons = new Buttons();
    ShowTable showTable = new ShowTable();

    // 외부 클래스 => 생성자
    public ManageSystem() {
        OUTTER : while(true) {
            ImageIcon icon = new ImageIcon("src/Images/image3.jpg");
            JOptionPane.showMessageDialog(null, null, "고객정보 관리 시스템", JOptionPane.NO_OPTION, icon); //JOptionPane.NO_OPTION => '확인'버튼 생성

            String password = JOptionPane.showInputDialog("고객 관리 시스템" + "\n" + "패스워드 입력");
            String passwd = "suyeon2205";

            if (password == null) {
                break OUTTER;
            } else if (password.equals(passwd)) { //  패스워드 인증 처리 성공 시 => 실행 화면을 띄우기
                setTitle("고객정보 관리 시스템");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                add(menuMain.bar, BorderLayout.NORTH);
                add(west, BorderLayout.WEST);
                add(buttons, BorderLayout.SOUTH);
                add(showTable.scrollPane, BorderLayout.CENTER);

                setSize(1200 , 800);
                setLocation(500, 50);
                setVisible(true);

                break OUTTER;
            } else {
                JOptionPane.showConfirmDialog(null, "패스워드가 맞지 않습니다." + "\n" + "'확인'버튼을 누르세요", "에러 메시지", JOptionPane.ERROR_MESSAGE);
                continue OUTTER;
            }
        }
    }
    // 메뉴 만들기 => 내부 클래스(inner class )
    class MenuMain extends JPanel implements ActionListener, ItemListener{
        JMenuBar bar;
        JMenu file, sort, help;
        JMenuItem fnew, fopen, fsave, fexit, proinfo;
        JCheckBoxMenuItem sno, sname, schul, sjob;

        FileDialog readOpen, saveOpen;
        String fileDir, fileName, savefileName, readfileName;
        ButtonGroup gr= new ButtonGroup();

        // 생성자
        public MenuMain() { //화면 구현(UI), 각 객체에 대한 이벤트 연결
            //객체 생성
            bar = new JMenuBar();

            file = new JMenu("파일");
            sort = new JMenu("정렬");
            help = new JMenu("도움말");

            fopen = new JMenuItem("열기"); //JMenuItem 컨포넌트는 actionEvent가 발생
            fsave = new JMenuItem("저장");
            fexit = new JMenuItem("닫기");

            sno = new JCheckBoxMenuItem("번호"); //JCheckBoxMenuItem 컴포넌트는 ItemEvent가 발생
            sname = new JCheckBoxMenuItem("이름");
            schul = new JCheckBoxMenuItem("출신지역");
            sjob = new JCheckBoxMenuItem("직업");

            proinfo = new JMenuItem("프로그램 정보");

            //객체들 컨테이너에 붙이기
            bar.add(file);
            bar.add(sort);
            bar.add(help);

            file.add(fopen);
            file.add(fsave);
            file.addSeparator();
            file.add(fexit);

            gr.add(sno);
            gr.add(sname);
            gr.add(schul);
            gr.add(sjob);

            sort.add(sno);
            sort.add(sname);
            sort.add(schul);
            sort.add(sjob);

            help.add(proinfo);

            //파일 메뉴 이벤트 연결
            fopen.addActionListener(this); //this는 이벤트 핸들러 리스너 객체
            fsave.addActionListener(this);
            fexit.addActionListener(this);

            //정렬 메뉴 이벤트 연결
            sno.addItemListener(this);
            sname.addItemListener(this);
            schul.addItemListener(this);
            sjob.addItemListener(this);
        }
        @Override
        public void itemStateChanged(ItemEvent e){

        }
        @Override
        public void actionPerformed(ActionEvent e){

        }

    } // end MenuMain 클래스 =======================================================================================

    // 입력, 신상 정보 즉 West => 내부 클래스
    class West extends JPanel {  //JPanel은 작은 컨테이너 역할의 클래스

        //내부 클래스 객체 생성
        Input input = new Input();
        Output output= new Output();

        // 생성자
        public West() {
            setLayout(new BorderLayout());
            add(input, BorderLayout.CENTER);
            add(output, BorderLayout.SOUTH);
        }
        class Input extends JPanel {     // 입력정보 담당 클래스
            JTextField[] tf= new JTextField[5];
            String[] text= {"번호","이름","핸드폰번호","이메일","주민등록번호"};
            String[] jobtext= {"선택","회사원","공무원","프로그래머","교수","학생","기타"};
            JLabel la, job;
            JComboBox box;

            // 생성자
            public Input(){ //화면 설계(UI)
                setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"입력"));
                setLayout(new GridLayout(6,2,5,30));

                for(int i=0; i<text.length; i++){
                    la= new JLabel(text[i]);
                    tf[i]= new JTextField(10);
                    la.setHorizontalAlignment(JLabel.CENTER);
                    add(la); add(tf[i]);
                }

                box= new JComboBox(jobtext);
                job= new JLabel("직업");
                job.setHorizontalAlignment(JLabel.CENTER);
                add(job); add(box);
                setPreferredSize(new Dimension(340,300));

            }
        }
        class Output extends JPanel implements ActionListener {  // 신상 정보 담당 클래스

            //추가
            JPanel info= new JPanel(); //신상 정보 보더만들기! JPanel (신상정보)
            JPanel search= new JPanel(); //신상 정보 보더만들기! JPanel (정보검색)

            CardLayout card= new CardLayout();
            String[ ] text= {" 나이:", " 성별:", " 출생지역:"," 생일:"};
            JLabel[] label= new JLabel[4];
            ButtonGroup group= new ButtonGroup();
            JRadioButton[] searchRadio= new JRadioButton[4];
            JButton searchButton;
            JButton exitButtion;
            JTextField nameText;
            String[] search_name= {"이름","직업","출생지역","생년월일"};
            int searchType;

            // 생성자
            public Output(){ //화면구현(UI), 각 객체(컴퍼넌트)에 대한 이벤트 연결
                //'신상 정보' 보더 만들기
                info.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"신상정보"));
                info.setLayout(new GridLayout(4,1));

                for(int i=0;i<text.length;i++){
                    label[i]=  new JLabel(text[i],JLabel.LEFT);
                    info.add(label[i]);
                }

                //'정보 검색' 보더 만들기
                search.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2)," 정보검색"));

                nameText= new JTextField(10);
                searchButton= new JButton("찾기");
                exitButtion= new JButton("나가기");

                searchButton.setBackground(Color.CYAN);
                exitButtion.setBackground(Color.LIGHT_GRAY);

                //버튼 객체에 이벤트 핸들러 리스너 등록
                searchButton.addActionListener(this);
                exitButtion.addActionListener(this);

                setPreferredSize(new Dimension(340,300));

                int x= -70;
                search.setLayout(null);
                for(int i=0;i<searchRadio.length;i++){
                    searchRadio[i]= new JRadioButton(search_name[i]);
                    searchRadio[i].setBounds(x+=80,60,80,30);
                    group.add(searchRadio[i]);
                    search.add(searchRadio[i]);

                    //이벤트 연결
                    searchRadio[i].addActionListener(this);
                }
                nameText.setBounds(40,110,200,30);
                searchButton.setBounds(35,170,80,30);
                exitButtion.setBounds(140,170,110,30);

                search.add(nameText);
                search.add(searchButton);
                search.add(exitButtion);

                setLayout(card);

                add(info,"신상정보 카드");
                add(search,"정보검색 카드");
            }

            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("나가기")) exit();
            }

            public void exit() {
                //west.output.card.next(west.output);
                west.output.card.show(west.output,"신상정보 카드");
            }
        }
    } // end West 클래스 ==========================================================================================

    // 버튼들 만들기 => 내부 클래스
    class Buttons extends JPanel implements ActionListener{
        Vector<String> vector;
        JButton addBtn, delBtn, preBtn, nextBtn, updateBtn, searchBtn;
        String juminNo;

        // 생성자
        public Buttons(){
            setLayout(new GridLayout(1,6));

            addBtn = new JButton("추가");
            delBtn = new JButton("삭제");
            preBtn = new JButton("이전");
            nextBtn = new JButton("다음");
            updateBtn = new JButton("수정");
            searchBtn = new JButton("검색");

            addBtn.setBackground(Color.YELLOW);
            delBtn.setBackground(Color.LIGHT_GRAY);
            updateBtn.setBackground(Color.PINK);
            searchBtn.setBackground(Color.GREEN);

            add(addBtn); add(delBtn); add(preBtn);
            add(nextBtn); add(updateBtn); add(searchBtn);

            //이벤트 연결
            addBtn.addActionListener(this);
            delBtn.addActionListener(this);
            preBtn.addActionListener(this);
            nextBtn.addActionListener(this);
            updateBtn.addActionListener(this);
            searchBtn.addActionListener(this);

        }

        @Override //재정의
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("추가")) addData();
            else if(e.getActionCommand().equals("검색")) searchData();
        }
        //'추가' 버튼 클릭 시 이벤트 처리 메소드 => 가장 중요한 메소드
        public void addData() {
            nextBtn.setEnabled(true); //'다음' 버튼 활성화
            vector= new Vector<String>();
            vector.add(west.input.tf[0].getText()); //번호 입력 값 얻어서 벡터 객체에 추가
            vector.add(west.input.tf[1].getText()); //이름
            vector.add(west.input.tf[2].getText()); //핸드폰번호
            vector.add(west.input.tf[3].getText()); //이메일
           // vector.add(west.input.tf[4].getText()); //주민번호 => 바로 넣는것x => 유효성 검사 필요!

            juminNo= west.input.tf[4].getText(); //주빈번호

            int sum= 0; //누계 변수는 0으로 초기화
            int[] weight= {2,3,4,5,6,7,0,8,9,2,3,4,5}; //가중치 배열 초기화
            int temp, result;

            String regex= "^[0-9]{6}-[1234][0-9]{6}$"; //주민 번호 정규표현식
            boolean check= juminNo.matches(regex);

            if(check==false){
                JOptionPane.showMessageDialog(null,"주민번호가 정규식 패턴에 맞지 않음\n주민번호를 다시 입력하세요!","에러 메시지", JOptionPane.ERROR_MESSAGE );
                west.input.tf[4].setText(null);
                west.input.tf[4].requestFocus();
                return; //현상태를 그대로 유지해라!
            }else if(juminNo.length()==14){
                //1단계=> sum 구하기
                for(int i=0;i<juminNo.length()-1;i++) {
                    if(juminNo.charAt(i)=='-') continue;
                    int gop=(juminNo.charAt(i)-'0')* weight[i]; //0의 ASCII코드 값(48)을 빼줌
                    sum+= gop;
                }

                //2단계 공식=> temp값이 두 자리인 주민번호를 가진 경우가 있다. 10 or 11
                temp= 11- (sum%11);

                //3단계 공식=> temp값이 두 자리인 주민번호인 경우를 대비해서 ... 한번 더!
                result= temp%10;

                //주민번호 정상 유무 체크=> return
                if(result==(juminNo.charAt(juminNo.length()-1)-'0')){ //주민번호가 정상일 때
                    JOptionPane.showMessageDialog(null,"주민번호 체크결과 정상입니다.\n'확인'버튼을 누르면 정보가 출력됩니다.");
                    vector.add(juminNo); //주민번호

                    //1. "나이" 추출
                    int isBefore= juminNo.charAt(7)-'0';
                    String year="";
                    if(isBefore==1||isBefore==2) { //isBefore<3
                        //00전
                        year+= "19";
                    }else {
                        //00이후
                        year+= "20";
                    }
                    year+=juminNo.substring(0, 2); //0 index부터 2-0=2자리 추출
                    int age= Calendar.getInstance(Locale.KOREA).get(Calendar.YEAR)- Integer.parseInt(year)+1; //나이
                    vector.add(String.valueOf(age));

                    //2. "성별" 추출
                    boolean isFemale=false; //성별
                    if(isBefore==2||isBefore==4) { //isBefore%2==0
                        isFemale= true;
                    }
                    vector.add(isFemale?"여":"남");

                    //3. "출신지역" 추출
                    //지역코드를 2차원 배열로 초기화
                    String[][] areaCode= {{"서울","00","08"},{"부산","09","12"},
                            {"인천","13","15"},{"경기","16","25"},{"강원","26","34"},
                            {"충북","35","39"},{"대전","40","40"},{"충남","41","43"},
                            {"충남","45","47"},{"세종","44","44"},{"세종","96","96"},
                            {"전북","48","54"},{"전남","55","64"},{"광주","65","66"},
                            {"대구","67","70"},{"경북","71","80"},{"경남","81","84"},
                            {"경남","86","90"},{"울산","85","85"},{"제주","91","95"}};
                    int area= Integer.parseInt(juminNo.substring(8, 10));

                    String area_name= null; //출생지역
                    for(int j=0; j<20; j++) {
                        if(area>=Integer.parseInt(areaCode[j][1])&&
                                area<=Integer.parseInt(areaCode[j][2]))
                            area_name=areaCode[j][0];
                    }
                    vector.add(area_name);

                    //4. "생년월일" 추출
                    String sang= year+"/"+juminNo.substring(2,4)+"/"+juminNo.substring(4,6);
                    vector.add(sang);

                    vector.add(west.input.box.getSelectedItem().toString()); //이메일

                    //output 화면을 통해 사용자에게 추가한 정보를 보여줌
                    west.output.label[0].setText(" 나이:"+" "+String.valueOf(age));
                    west.output.label[1].setText(" 성별:"+" "+(isFemale?"여":"남"));
                    west.output.label[2].setText(" 출생지역:"+" "+area_name);
                    west.output.label[3].setText(" 생일:"+" "+sang);

                    //입력받는 text, comboBox 초기화(다음 정보를 추가하기 위해)
                    west.input.tf[0].setText(null); //번호
                    west.input.tf[1].setText(null); //아룸
                    west.input.tf[2].setText(null); //핸드폰번호
                    west.input.tf[3].setText(null); //이메일
                    west.input.tf[4].setText(null); //주민번호
                    west.input.box.setSelectedIndex(0); //ComboBox 객체에 '선택'으로 원위치
                    west.input.tf[0].requestFocus(); //번호에 포커스를 맞춰라!

                    showTable.data.addElement(vector);
                    showTable.datamodel.fireTableDataChanged();
                }else{
                    JOptionPane.showMessageDialog(null,"주민번호 틀림","에러 메시지", JOptionPane.ERROR_MESSAGE );
                    west.input.tf[4].setText(null);
                    west.input.tf[4].requestFocus();
                }
            }
        }

        //'검색' 버튼 클릭 시 이벤트 처리 메소드
        public void searchData() {
            //west.output.card.next(west.output);
            west.output.card.show(west.output,"정보검색 카드");
        }
    } // end Buttons 클래스 =====================================================================================

    // 센터쪽 => Jtable 만들기 => 내부 클래스
    class ShowTable extends MouseAdapter {
        DefaultTableModel datamodel;
        JTable table;
        JScrollPane scrollPane;

        String[] colName = {"번호","이름","핸드폰번호","이메일","주민번호","나이","성별","출생지역","생일","직업"};
        Vector<Vector<String>> data; //[중요] Vector<String> -> 한 사람의 정보
        Vector<String> column_name;
        int row= -1;

        // 생성자
        public ShowTable(){
            data= new Vector<Vector<String>>();
            column_name= new Vector<String>();

            for(int i=0;i<colName.length;i++){
                column_name.add(colName[i]);
            }

            //1단계
            datamodel= new DefaultTableModel(data, column_name){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            //2단계
            table= new JTable(datamodel);

            //3단계
            scrollPane= new JScrollPane(table);

            //이벤트 연결
            table.addMouseListener(this);
            table.setAutoCreateRowSorter(true);

            TableRowSorter<TableModel> tablesorter= new TableRowSorter<TableModel>(table.getModel());
            table.setRowSorter(tablesorter);
            tableSize();
        }

        public void tableSize(){
            //JTable 셀크기 조절하기
            table.getColumnModel().getColumn(0).setPreferredWidth(50); //'번호' 셀
            table.getColumnModel().getColumn(1).setPreferredWidth(50); //'이름' 셀
            table.getColumnModel().getColumn(2).setPreferredWidth(120); //'핸드폰번호' 셀
            table.getColumnModel().getColumn(3).setPreferredWidth(150); //'이메일' 셀
            table.getColumnModel().getColumn(4).setPreferredWidth(130); //'주민번호' 셀
            table.getColumnModel().getColumn(5).setPreferredWidth(50); //'나이' 셀
            table.getColumnModel().getColumn(6).setPreferredWidth(50); //'성별' 셀
            table.getColumnModel().getColumn(7).setPreferredWidth(80); //'출생지역' 셀
            table.getColumnModel().getColumn(8).setPreferredWidth(70); //'생일' 셀
            table.getColumnModel().getColumn(9).setPreferredWidth(50); //'직업' 셀

        }

    }

    public static void main(String[] args) {
        ManageSystem ms = new ManageSystem();
    }
}
