/*
NetPaint

     by T. Yamanoue,
     yamanoue@isc.kyutech.ac.jp
     Kyushu Institute of Technology, Japan,
     Aug.1, 1997


   A Paint tool for the Internet.

   Drawing tool on a Web brouser.
   A Co-operative drawing tool.
   Drawing a paint on the Internet by linking parts


*/
/* NetPaint.java

   GUI of this program is generated by Symantic Visual Cafe.

*/


/*
    �ȉ��̺��ނ� java.applet.Applet �׽���g���������̂ł�.
 */

import java.awt.*;

import javax.swing.JFrame;

import java.applet.*;

public class TESLA2 extends JFrame {
	void fClear_Clicked(Event event) {


		//{{CONNECTION
		// TextField �̂��ׂĂ̕�����I������
		// editFunField.selectAll();
		//}}
		factory.fs=null;
		factory.repaint();
	}

	void eClearButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �̂��ׂĂ̕�����I������
		// editFunField.selectAll();
		//}}
		fcanvas.fs.bp=null;
		fcanvas.repaint();
	}

	void telePortButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �̂��ׂĂ̕�����I������
	//	editFunField.selectAll();
		//}}
		if(fcanvas.fs!=null)
		  if(fcanvas.fs.bp!=null){
		    if(fcanvas.ftemp!=null &&
		       editdispatch.state.equals("Select")){
		        fcanvas.ftemp.selected=false;
		        fcanvas.fs.add(fcanvas.ftemp);
		        fcanvas.ftemp=null;
		        fcanvas.repaint();
	           }
		    ainfo.init();
		    fcanvas.fs.bp.block.analyze(
		          fcanvas.fs, "B0",ainfo);
		    ainfo.reConstruct1(fcanvas);
	//	    fcanvas.fs.bp=ainfo.bl.bp;
	//	    fcanvas.repaint();
		  }
	}

    AnalizedInfos ainfo;
	void eAnalyzeButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
		//editFunField.setText("Analyze");
		//}}
		if(fcanvas.fs!=null)
		  if(fcanvas.fs.bp!=null){
		    ainfo.init();
		    fcanvas.fs.bp.block.analyze(
		          fcanvas.fs, "B0",ainfo);
		    ainfo.reConstruct1(fcanvas);
		    fcanvas.fs.bp=ainfo.bl.bp;
		    fcanvas.repaint();
		  }
	}

    public FactoryController factorycontroller;
	void fBlock3Button_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
//		figNamField.setText("Block3");
		//}}
	    editdispatch.newBlock("Block3");
	}

	void fBlock2Button_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
	//	figNamField.setText("Block2");
		//}}
	    editdispatch.newBlock("Block2");
	}

	void fBlock1Button_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
	//	figNamField.setText("Block1");
		//}}
		editdispatch.newBlock("Block1");
	}

	void eRotateEWButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
	//	editFunField.setText("RotateEW");
 		//}}
       editdispatch.Edit("RotateEW");

	}


	void eRotateNSButton_Clicked(Event event) {

		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
//		editFunField.setText("RotateNS");
		//}}
		editdispatch.Edit("RotateNS");

	}

	void eMoveButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
	//	editFunField.setText("Move");
		//}}
        editdispatch.Edit("Move");
	}

	void eCopyButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
		editFunField.setText("Copy");
		//}}
	}


    public EditDispatcher editdispatch;
	void fcanvas_MouseMove(Event event) {


		//{{CONNECTION
		// TextArea �ɕ������ǉ�����...
		textArea1.appendText("Mouse Move "+event.x+","+event.y+"\n");
		//}}
		editdispatch.mouseMove(event,event.x,event.y);
	}

	void fcanvas_MouseExit(Event event) {


		//{{CONNECTION
		// TextArea �ɕ������ǉ�����...
		textArea1.appendText("Mous Cursor out of the Canvas\n");
		//}}
		editdispatch.mouseExit(event);
	}

	void fcanvas_MouseEnter(Event event) {


		//{{CONNECTION
		// TextArea �ɕ������ݒ肷��...
		textArea1.setText("Mous Cursor in the Canvas\n");
		//}}
		editdispatch.mouseEnter(event,event.x, event.y);
	}

	void fcanvas_MouseDrag(Event event) {


		//{{CONNECTION
		// TextArea �ɕ������ǉ�����...
		textArea1.appendText("Mous Drag\n");
		//}}
		editdispatch.mouseDrag(event, event.x,event.y);
	}

	void fcanvas_MouseUp(Event event) {


		//{{CONNECTION
		// TextArea �ɕ������ǉ�����...
		textArea1.appendText("Mous Button Up\n");
		//}}
		editdispatch.mouseUp(event,event.x,event.y);
	}

	void fcanvas_MouseDown(Event event) {


		//{{CONNECTION
		// TextArea �ɕ������ǉ�����...
		textArea1.appendText("Mouse Button Down "+event.x+","+event.y+"\n");
		//}}
		editdispatch.mouseDown(event, event.x, event.y);
	}

	void eCutButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
//		editFunField.setText("Cut");
		//}}
//	    editdispatch.changeMode("Cut");
	    editdispatch.rmBlock();
	}


	void eSelectButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
//		editFunField.setText("Select");
		//}}
		 editdispatch.select();
	}


	void eNewButton_Clicked(Event event) {


		//{{CONNECTION
		// TextField �ɕ������ݒ肷��...
//		editFunField.setText("New");
		//}}
        editdispatch.newBlock(figNamField.getText());

	}



	public void init() {

	// symantec.itools.net.RelativeURL ���g�p���Ȃ��ꍇ�͎���1�s�͍폜���Ă�������.
/*
		symantec.itools.lang.Context.setDocumentBase(getDocumentBase());
*/
//        fcanvas = new FigCanvas();
//		fcanvas.reshape(204,96,300,276);
//		fcanvas.setBackground(new Color(11791252));
//		add(fcanvas);

		//{{INIT_CONTROLS
		setLayout(null);
		addNotify();
		resize(768,880);
		label1 = new java.awt.Label("Edit");
		label1.reshape(36,12,53,12);
		add(label1);
		label2 = new java.awt.Label("Figure");
		label2.reshape(132,12,48,14);
		add(label2);
		figNamField = new java.awt.TextField();
		figNamField.reshape(132,36,60,24);
		add(figNamField);
		fBlock1Button = new java.awt.Button("Block 1");
		fBlock1Button.reshape(132,72,60,24);
		add(fBlock1Button);
		fBlock2Button = new java.awt.Button("Block 2");
		fBlock2Button.reshape(132,108,60,24);
		add(fBlock2Button);
		eNewButton = new java.awt.Button("New");
		eNewButton.reshape(36,72,60,24);
		add(eNewButton);
		editFunField = new java.awt.TextField();
		editFunField.reshape(36,36,60,24);
		add(editFunField);
		eSelectButton = new java.awt.Button("Select");
		eSelectButton.reshape(36,108,60,24);
		add(eSelectButton);
		eCutButton = new java.awt.Button("Cut");
		eCutButton.reshape(36,144,60,24);
		add(eCutButton);
		eMoveButton = new java.awt.Button("Move");
		eMoveButton.reshape(36,216,60,24);
		add(eMoveButton);
		eRotateNSButton = new java.awt.Button("Rotate NS");
		eRotateNSButton.reshape(132,180,60,24);
		add(eRotateNSButton);
		eRotateEWButton = new java.awt.Button("Rotate EW");
		eRotateEWButton.reshape(132,216,60,24);
		add(eRotateEWButton);
		eCopyButton = new java.awt.Button("Copy");
		eCopyButton.reshape(36,180,60,24);
		add(eCopyButton);
		fBlock3Button = new java.awt.Button("Block 3");
		fBlock3Button.reshape(132,144,60,24);
		add(fBlock3Button);
		eClearButton = new java.awt.Button("Clear");
		eClearButton.reshape(84,252,60,24);
		add(eClearButton);
		fcanvas = new FigCanvas();
		fcanvas.reshape(220,30,400,280);
		fcanvas.setBackground(new Color(8454016));
		add(fcanvas);
		verticalScrollbar1 = new java.awt.Scrollbar(Scrollbar.VERTICAL);
		verticalScrollbar1.reshape(620,30,20,280);
		add(verticalScrollbar1);
		horizontalScrollbar1 = new java.awt.Scrollbar(Scrollbar.HORIZONTAL);
		horizontalScrollbar1.reshape(220,310,400,20);
		add(horizontalScrollbar1);
		textArea1 = new java.awt.TextArea();
		textArea1.reshape(24,396,180,144);
		add(textArea1);
		label4 = new java.awt.Label("Messages");
		label4.reshape(108,564,72,40);
		add(label4);
		factory = new Factory();
		factory.reshape(220,340,400,450);
		factory.setBackground(new Color(8454016));
		add(factory);
		horizontalScrollbar2 = new java.awt.Scrollbar(Scrollbar.HORIZONTAL);
		horizontalScrollbar2.reshape(220,790,400,20);
		add(horizontalScrollbar2);
		verticalScrollbar2 = new java.awt.Scrollbar(Scrollbar.VERTICAL);
		verticalScrollbar2.reshape(620,340,20,450);
		add(verticalScrollbar2);
		telePortButton = new java.awt.Button("Teleportation");
		telePortButton.reshape(48,324,132,24);
		add(telePortButton);
		fClear = new java.awt.Button("Clear");
		fClear.reshape(84,360,60,24);
		add(fClear);
		//}}

		/* */
		fcanvas.init();
		editdispatch=new EditDispatcher(fcanvas, textArea1, this);
		/* */
        editdispatch.newBlock("Block1");
        /* */
        factory.init();
        factorycontroller=new FactoryController(factory,this);
        factory.fcontroller=factorycontroller;
        ainfo=new AnalizedInfos();
        ainfo.factoryCntlr=factorycontroller;
	}


	public boolean handleEvent(Event event) {
		if (event.target == eNewButton && event.id == Event.ACTION_EVENT) {
			eNewButton_Clicked(event);
			return true;
		}
		if (event.target == eSelectButton && event.id == Event.ACTION_EVENT) {
			eSelectButton_Clicked(event);
			return true;
		}
		if (event.target == eCutButton && event.id == Event.ACTION_EVENT) {
			eCutButton_Clicked(event);
			return true;
		}
		if (event.target == fcanvas && event.id == Event.MOUSE_DOWN) {
			fcanvas_MouseDown(event);
			return true;
		}
		if (event.target == fcanvas && event.id == Event.MOUSE_UP) {
			fcanvas_MouseUp(event);
			return true;
		}
		if (event.target == fcanvas && event.id == Event.MOUSE_DRAG) {
			fcanvas_MouseDrag(event);
			return true;
		}
		if (event.target == fcanvas && event.id == Event.MOUSE_ENTER) {
			fcanvas_MouseEnter(event);
			return true;
		}
		if (event.target == fcanvas && event.id == Event.MOUSE_EXIT) {
			fcanvas_MouseExit(event);
			return true;
		}
		if (event.target == fcanvas && event.id == Event.MOUSE_MOVE) {
			fcanvas_MouseMove(event);
			return true;
		}
		if (event.target == eCopyButton && event.id == Event.ACTION_EVENT) {
			eCopyButton_Clicked(event);
			return true;
		}
		if (event.target == eMoveButton && event.id == Event.ACTION_EVENT) {
			eMoveButton_Clicked(event);
			return true;
		}
		if (event.target == eRotateEWButton && event.id == Event.ACTION_EVENT) {
			eRotateEWButton_Clicked(event);
			return true;
		}
		if (event.target == eRotateNSButton && event.id == Event.ACTION_EVENT) {
			eRotateNSButton_Clicked(event);
			return true;
		}
		if (event.target == fBlock1Button && event.id == Event.ACTION_EVENT) {
			fBlock1Button_Clicked(event);
			return true;
		}
		if (event.target == fBlock2Button && event.id == Event.ACTION_EVENT) {
			fBlock2Button_Clicked(event);
			return true;
		}
		if (event.target == fBlock3Button && event.id == Event.ACTION_EVENT) {
			fBlock3Button_Clicked(event);
			return true;
		}
		if (event.target == telePortButton && event.id == Event.ACTION_EVENT) {
			telePortButton_Clicked(event);
			return true;
		}
		if (event.target == eClearButton && event.id == Event.ACTION_EVENT) {
			eClearButton_Clicked(event);
			return true;
		}
		if (event.target == fClear && event.id == Event.ACTION_EVENT) {
			fClear_Clicked(event);
			return true;
		}
		return super.handleEvent(event);
	}



	//{{DECLARE_CONTROLS
	java.awt.Label label1;
	java.awt.Label label2;
	java.awt.TextField figNamField;
	java.awt.Button fBlock1Button;
	java.awt.Button fBlock2Button;
	java.awt.Button eNewButton;
	java.awt.TextField editFunField;
	java.awt.Button eSelectButton;
	java.awt.Button eCutButton;
	java.awt.Button eMoveButton;
	java.awt.Button eRotateNSButton;
	java.awt.Button eRotateEWButton;
	java.awt.Button eCopyButton;
	java.awt.Button fBlock3Button;
	java.awt.Button eClearButton;
	FigCanvas fcanvas;
	java.awt.Scrollbar verticalScrollbar1;
	java.awt.Scrollbar horizontalScrollbar1;
	java.awt.TextArea textArea1;
	java.awt.Label label4;
	Factory factory;
	java.awt.Scrollbar horizontalScrollbar2;
	java.awt.Scrollbar verticalScrollbar2;
	java.awt.Button telePortButton;
	java.awt.Button fClear;
	//}}
	
	public TESLA2(){
		init();
	}
	
	public static void main(String[] args){
		TESLA2 t2=new TESLA2();
		t2.setVisible(true);
	}
}