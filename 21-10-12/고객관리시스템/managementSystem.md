### 고객 정보 '수정' 기능 추가하기  
1. Buttons 클래스 내 actionPerformed 메소드에 '수정' 버튼 클릭 시 처리할 메소드를 추가해준다.  
  
```java
public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("추가")) addData();
            else if(e.getActionCommand().equals("검색")) searchData();
            else if(e.getActionCommand().equals("수정")) updateData(); //추가한 코드
        }
```

2. '수정' 메소드 updateData() 생성  
  
```java
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
```

==============================================================================================
  

### 오른 쪽 고객 정보에서 하나의 고객 정보를 선택 하면, 왼쪽 입력 박스에 해당 정보 표시 (수정 기능과 관련)  
- ShowTable 메소드에서 MouseAdapter를 상속받음    
- MouseListener대신 MouseAdaptor 사용  
⇒ 다섯 개 메소드를 모두 구현 X , MouseClicked() 만 구현!  
  
1. ShowTable 클래스 내부에 MouseClicked() 메소드 override  
⇒ 마우스 클릭 시, Info() 메소드 호출  
  
```java
//필요한 메소드를 수동으로 override
@Override
 public void mouseClicked(MouseEvent e) {
       row= table.getSelectedRow();

       //JTable에 있는 데이터를 west객체 input객체의 JTextField 객체에 보여주기
       Info();
 }
```
  
2. Info() 메소드 구현  
  
```java
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
```

==================================================================================================================================================  
  
### 고객 정보 '삭제' 기능 구현하기  
1. Buttons 클래스 내 actionPerformed 메소드에 '삭제' 버튼 클릭 시 처리할 메소드 deleteData()를 추가한다.  
  
```java
@Override //재정의
public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("추가")) addData();
    else if(e.getActionCommand().equals("검색")) searchData();
    else if(e.getActionCommand().equals("수정")) updateData();
    else if(e.getActionCommand().equals("삭제")) deleteData(); //추가한 코드
}
```
  
2. deleteData() 구현  
  
```java
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
```
  
==================================================================================================================================================  

### 검색- '찾기' 기능 구현  
search() 메소드, searchType 변수 사용  
  
1. Output 클래스 내 actionPerformed 메소드에 찾기 선택 시 처리할 이벤트 함수(search()) 호출  
  
```java
@Override
public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("이름")) searchType=1;
		if(e.getActionCommand().equals("직업")) searchType=9;
		if(e.getActionCommand().equals("출생지역")) searchType=7;
		if(e.getActionCommand().equals("생일")) searchType=8;
		if(e.getActionCommand().equals("찾기")) search();
		if(e.getActionCommand().equals("나가기")) exit();
}
```  
  
2. search() 함수 구현  
  
```java
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
```  
  
==================================================================================================================================================  

### 파일 입출력 기능 구현  
⇒ MenuMain 클래스 내부의 actionPerformed 메소드에서 열기,저장,닫기 기능을 각각 처리 할 메소드를 호출한다.   
  
```java
@Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("열기")) open();
            else if(e.getActionCommand().equals("저장")) save();
            else if(e.getActionCommand().equals("닫기")) exit();
        }
```  
  
- '파일 열기' 이벤트 처리 메소드 open() 구현  
  
```java
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
```
  
- '파일 저장' 이벤트 처리 메소드 save() 구현  
  
```java
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
```  
  
- '파일 닫기' 이벤트 처리 메소드 exit() 구현  
  
```java 
//'파일 닫기' 이벤트 처리 메소드
        public void exit(){
            System.exit(0);
        }
```
  
  
