package 고객관리구축;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

/*
        << 개정 이력 ( Modification Infomation ) >>
        수정일             수정자             작업 내용/ 수정내용
       --------         -----------          ---------------------
       2021.10.07         최수연              레이아웃 설계, 기능 구현,
       2021.10.08         최수연
       2021.10.08         최수연              수정, 삭제, 검색 기능 구현

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
        String fileDir, fileName, savefileName, readfileName; //전역 객체 참조변수 선언
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
            if(e.getSource().equals(sno)) numSort(); //'번호' 정렬
            else if(e.getSource().equals(sname)) stringSort(1); //'이름' 정렬
            else if(e.getSource().equals(schul)) stringSort(7); //'출신지역' 정렬
            else if(e.getSource().equals(sjob)) stringSort(9); //'직업' 정렬
        }

        //'번호' 정렬 ===================
        public void numSort() {
            int row= showTable.table.getRowCount(); //행의 갯수 얻기
            int col= showTable.table.getColumnCount(); //열의 갯수 얻기

            String temp;
            String[][] arr= new String[row][col]; //2차원 배열 객체 생성

            //먼저 JTable 객체의 데이터들을 arr[][] 2차원 배열로 옮기기
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    arr[i][j]= (String)showTable.table.getValueAt(i,j);
                }
            }

            //선택 정렬(selection sort) 알고리즘을 적용하여 2차원 배열 정렬하기
            for(int i=0;i<row-1;i++){
                for(int j=i+1;j<row-1;j++){
                    if(Integer.parseInt(arr[i][0])>Integer.parseInt(arr[j][0])){
                        for(int k=0;k<col;k++){
                            temp= arr[i][k];
                            arr[i][k]= arr[j][k];
                            arr[j][k]= temp;
                        }
                    }

                }
            }
            //arr[][] 2차원 배열의 데이터들을  JTable로 옮기기
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    showTable.table.setValueAt(arr[i][j],i,j);   //매개변수 value, row, col
                }
            }

        }

        // 하나의 메소드로 '이름', '출신지역', '직업' 정렬을 처리한다.
        public void stringSort(int sortType){
            int row = showTable.table.getRowCount();    // 행의 갯수 얻기
            int col = showTable.table.getColumnCount(); // 열의 갯수 얻기

            String temp;
            String[][] arr = new String[row][col];    // 2차원 배열 객체 생성

            // 먼저 JTable 객체의 데이터들을 arr 2차원 배열로 옮기기
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    arr[i][j] = (String)showTable.table.getValueAt(i, j);
                }
            }
            // 선택정렬 (selection sort) 알고리즘을 적용하여 2차원 배열 정렬하기
            for(int i=0; i<row-1; i++){
                for(int j=i+1; j<row-1; j++){
                    if(arr[i][sortType].compareTo(arr[j][sortType])>0){
                        for(int k=0; k<col; k++){
                            temp = arr[i][k];
                            arr[i][k] = arr[j][k];
                            arr[j][k] = temp;
                        }
                    }
                }
            }
            // arr[][] 2차원 배열의 데이터들을 JTable로 옮기기
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    showTable.table.setValueAt(arr[i][j],i,j);
                }
            }

        }

        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("열기")) open();
            else if(e.getActionCommand().equals("저장")) save();
            else if(e.getActionCommand().equals("닫기")) exit();
        }

        //'파일 열기' 이벤트 처리 메소드
        public void open(){
            StringTokenizer st;
            Vector v;

            //'문서 열기' 다이얼로그 창 띄우기
            readOpen= new FileDialog(ManageSystem.this, "문서열기", FileDialog.LOAD);
            readOpen.setVisible(true);

            fileDir= readOpen.getDirectory(); //사용자가 선택한 파일의 디렉토리 이름
            fileName= readOpen.getFile(); //사용자가 선택한 파일 이름

            readfileName= fileDir+"//"+fileName; //파일 전체 이름

            try{
                BufferedReader br= new BufferedReader(new FileReader(readfileName)); //파일을 읽어올 스트림 생성!
                String line= null;

                //읽어 올 파일 내용이 더이상 없을 때 까지 while문 실행
                while((line= br.readLine())!=null){
                    st= new StringTokenizer(line, ", "); //vector에 저장될 때 ", "으로 분리시켜 저장 된다.(중요!)
                    v= new Vector();

                    while(st.hasMoreTokens()){
                        v.add(st.nextToken());
                    }

                    showTable.data.addElement(v); //JTable객체 showTable에 데이터를 하나씩 추가해 줌!
                }
                showTable.datamodel.fireTableDataChanged(); //JTable의 데이터모델에 변화 있음을 통보
            }catch (Exception e){

            }
        }

        //'파일 저장' 이벤트 처리 메소드 => [가장 중요한 핵심 기능]
        public void save(){
            saveOpen= new FileDialog(ManageSystem.this, "문서저장", FileDialog.SAVE); //저장을 할 수 있는 창 생성
            saveOpen.setVisible(true); //위에서 생성한 '문서 저장' 창을 띄우기

            fileDir= saveOpen.getDirectory(); //사용자가 선택한 '디렉토리명' 얻어오기
            fileName= saveOpen.getName(); //사용자가 선택한 '파일명' 얻어오기
            savefileName= fileDir +"//"+ fileName; //결합

            String str="";
            String temp="";

            /*
            [중요] 파일에 저장 또는 열기 작업할 때는 반드시 예외처리 구문이 필요하다.!
             */
            try{
                //보조스트림    참조변수= new 보조스트림생성자(new 기본스트림생성자(디렉토리/파일명.txt ))
                BufferedWriter save= new BufferedWriter(new FileWriter(savefileName));

                //테이블에 있는 모든 고객 정보를 문자열화 => str 변수에 저장
                for(int i=0;i<showTable.table.getRowCount();i++){
                    temp= showTable.data.elementAt(i).toString();
                    str+= temp.substring(1, temp.length()-1)+"\n";
                }

                save.write(str); //저장
                save.close(); //자원해제
            }catch (Exception ex){

            }
        }

        //'파일 닫기' 이벤트 처리 메소드
        public void exit(){
            System.exit(0);
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
            } //output 생성자 ----종료

            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("이름")) searchType= 1;
                if(e.getActionCommand().equals("직업")) searchType= 9;
                if(e.getActionCommand().equals("출생지역")) searchType= 7;
                if(e.getActionCommand().equals("생일")) searchType= 8;
                if(e.getActionCommand().equals("찾기")) search();
                if(e.getActionCommand().equals("나가기")) exit();
            }

            //'찾기' 이벤트 처리 메소드 => [중요]
            public void search() {
                Vector v= new Vector();

                //사용자가 검색한 고객 정보를 vector에 저장
                for(int i=0;i<showTable.data.size();i++){
                    if(nameText.getText().equals(showTable.data.elementAt(i).get(searchType))){
                        v.addElement(showTable.data.elementAt(i));
                    }
                }

                //showtable의 datamodel-> data vector값을 뿌려줌
                showTable.datamodel.setDataVector(v, showTable.column_name);
                showTable.tableSize();
                nameText.setText(null);
            }

            //'나가기' 이벤트 처리 메소드
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
            else if(e.getActionCommand().equals("수정")) updateData();
            else if(e.getActionCommand().equals("삭제")) deleteData();
            else if(e.getActionCommand().equals("이전")) preData();
            else if(e.getActionCommand().equals("다음")) nextData();

        }

        //'다음' 버튼에서 이벤트가 발생하면 처리하는 메소드
        public void nextData() {
            /*
            showTable.row++;
            if(showTable.row>=showTable.data.size()) showTable.row= 0;

            //JTable에 있는 데이터를 west객체 input객체의 JTextField 객체에 보여주기
            showTable.Info();
            */
            if(showTable.row < showTable.datamodel.getRowCount()-1){
                showTable.row++;
                addBtn.setEnabled(false); //'추가' 버튼 비활성화
                preBtn.setEnabled(true);
            }else{
                nextBtn.setEnabled(false); //'다음'버튼 비활성화
                addBtn.setEnabled(true); //'추가' 버튼 활성화

                west.input.tf[0].setText(null);
                west.input.tf[1].setText(null);
                west.input.tf[2].setText(null);
                west.input.tf[3].setText(null);
                west.input.tf[4].setEnabled(true);
                west.input.tf[4].setText(null);
                west.input.box.setSelectedIndex(0);
                west.input.tf[0].requestFocus();

                west.output.label[0].setText(" 나이:");
                west.output.label[1].setText(" 성별:");
                west.output.label[2].setText(" 출생지역:");
                west.output.label[3].setText(" 생일:");

                return;
            }
            showTable.Info();
        }

        //'이전' 버튼에서 이벤트가 발생하면 처리하는 메소드
        public void preData() {
            /*
            showTable.row--;

            if(showTable.row<0) showTable.row= showTable.data.size()-1;
            //JTable에 있는 데이터를 west객체 input객체의 JTextField 객체에 보여주기
            showTable.Info();
             */

            if(showTable.row>0){
                showTable.row--;
                addBtn.setEnabled(false); //'추가' 버튼 비활성화
                nextBtn.setEnabled(true); //'다음'버튼 활성화
            }else{
                preBtn.setEnabled(false); //'이전'버튼 비활성화
                addBtn.setEnabled(true); //'추가' 버튼 활성화
            }
            showTable.Info();
        }

        //'삭제' 버튼에서 이벤트가 발생하면 처리하는 메소드
        public void deleteData() {
            int yes_no_select= JOptionPane.showConfirmDialog(null,"정말 삭제하시겠습니까?","삭제 확인",JOptionPane.YES_NO_OPTION);
            if(yes_no_select==JOptionPane.YES_OPTION){
                addBtn.setEnabled(true); //'추가' 버튼 활성화

                JTable tb= showTable.table;
                int deleteRow= tb.getSelectedRow();
                if(deleteRow == -1) {
                    //JTable 객체에서 행을 선택하지 않고 삭제 버튼을 누른 경우
                    return;  //return -1;
                }else {
                    //JTable 객체에서 행을 선택한 경우
                    DefaultTableModel model= (DefaultTableModel)tb.getModel();
                    model.removeRow(deleteRow); //Table 내 객체 삭제

                    west.input.tf[0].setText(null);
                    west.input.tf[1].setText(null);
                    west.input.tf[2].setText(null);
                    west.input.tf[3].setText(null);
                    west.input.tf[4].setText(null);
                    west.input.box.setSelectedIndex(0);

                    west.output.label[0].setText(" 나이:"+"");
                    west.output.label[1].setText(" 성별:"+"");
                    west.output.label[2].setText(" 출생지역:"+"");
                    west.output.label[3].setText(" 생일:"+"");
                    west.input.tf[0].requestFocus();
                }

            }
        }

        //'수정' 버튼에서 이벤트가 발생하면 처리하는 메소드
        public void updateData(){
            int updateRow= showTable.table.getSelectedRow(); //선택한 행의 인자 값

            //'핸드폰번호' 수정할 때
            showTable.table.setValueAt(west.input.tf[2].getText(),updateRow,2 ); //두번째 입력 값(핸드폰)-> 선택한 행의 두번째 열 update

            //'이메일' 수정할 때
            showTable.table.setValueAt(west.input.tf[3].getText(),updateRow,3);

            //'직업' 수정할 때
            showTable.table.setValueAt(west.input.box.getSelectedItem(), updateRow, 9);

            //입력 textbox 초기화
            west.input.tf[0].setText(null);
            west.input.tf[1].setText(null);
            west.input.tf[2].setText(null);
            west.input.tf[3].setText(null);
            west.input.tf[4].setText(null);
            west.input.box.setSelectedIndex(0);

            //데이터 수정 후 다음 데이터를 입력받기 위해서 주민번호 다시 활성화 시킨다.
            west.input.tf[4].setEditable(true);
            west.input.tf[0].requestFocus(); //포커스를 번호에..

            west.output.label[0].setText(" 나이:"+"");
            west.output.label[1].setText(" 성별:"+"");
            west.output.label[2].setText(" 출생지역:"+"");
            west.output.label[3].setText(" 생일:"+"");
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

            juminNo= west.input.tf[4].getText(); //주민번호

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
                    west.input.tf[1].setText(null); //이름
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
        } //end ShowTable() 생성자 -----------------

        //필요한 메소드를 수동으로 override
        @Override
        public void mouseClicked(MouseEvent e) {
            row= table.getSelectedRow();

            //JTable에 있는 데이터를 west객체 input객체의 JTextField 객체에 보여주기
            Info();
        }

        public void Info(){
            int row= this.row;

            //오른 쪽 테이블에서 사용자가 선택한 정보를 왼쪽 입력 textbox에 data를 넣는다.
            west.input.tf[0].setText((String)showTable.table.getValueAt(row,0));
            west.input.tf[1].setText((String)showTable.table.getValueAt(row,1));
            west.input.tf[2].setText((String)showTable.table.getValueAt(row,2));
            west.input.tf[3].setText((String)showTable.table.getValueAt(row,3));
            west.input.tf[4].setText((String)showTable.table.getValueAt(row,4));

            //주민 번호는 수정 못하게 비활성화 시킨다.
            west.input.tf[4].setEditable(false);
            west.input.box.setSelectedItem(showTable.table.getValueAt(row,9));

            west.output.label[0].setText(" 나이: "+showTable.table.getValueAt(row,5));
            west.output.label[1].setText(" 성별: "+showTable.table.getValueAt(row,6));
            west.output.label[2].setText(" 출생지역: "+showTable.table.getValueAt(row,7));
            west.output.label[3].setText(" 생일: "+showTable.table.getValueAt(row,8));

            showTable.table.changeSelection(row,0,false,false);
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
