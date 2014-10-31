import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import android.view.KeyEvent;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IShellOutputReceiver;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.android.uiautomator.tree.BasicTreeNode;
import com.android.uiautomator.tree.UiNode;
import com.android.ddmlib.IDevice;

public class Test extends UiAutomatorTestCase {

	private static BasicTreeNode mRootNode;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ACA");
		extractXml("");
		//initAdbConnection();
	}

	private static void extractXml(String xmlPath) {
		mRootNode = null;
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        } catch (SAXException e) {
            e.printStackTrace();
            return;
        }
		
        DefaultHandler handler = new DefaultHandler(){
            BasicTreeNode mParentNode;
            //BasicTreeNode mWorkingNode;
            @Override
            public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
                boolean nodeCreated = false;
                // starting an element implies that the element that has not yet been closed
                // will be the parent of the element that is being started here
                //mParentNode = mWorkingNode;
                if ("hierarchy".equals(qName)) {
                    //mWorkingNode = new RootWindowNode(attributes.getValue("windowName"));
                	System.out.println("Start: "+qName);
                    //nodeCreated = true;
                } else if ("node".equals(qName)) {
                	System.out.println("Start: "+qName);
                	for (int i = 0; i < attributes.getLength(); i++) {
                        System.out.println("->Atrib: "+attributes.getQName(i)+" "+attributes.getValue(i));
                    }
                    /*UiNode tmpNode = new UiNode();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        tmpNode.addAtrribute(attributes.getQName(i), attributes.getValue(i));
                    }
                    mWorkingNode = tmpNode;
                    nodeCreated = true;*/
                }
                // nodeCreated will be false if the element started is neither
                // "hierarchy" nor "node"
                /*if (nodeCreated) {
                    if (mRootNode == null) {
                        // this will only happen once
                        mRootNode = mWorkingNode;
                    }
                    if (mParentNode != null) {
                        mParentNode.addChild(mWorkingNode);
                    }
                }*/
                
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                //mParentNode should never be null here in a well formed XML
            	System.out.println("End "+qName);
                if (mParentNode != null) {
                    // closing an element implies that we are back to working on
                    // the parent node of the element just closed, i.e. continue to
                    // parse more child nodes
                    //mWorkingNode = mParentNode;
                    //mParentNode = mParentNode.getParent();
                }
            }
        };
        
        try {
            parser.parse(new File(xmlPath), handler);
        } catch (SAXException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
	}

	static class OutShell implements IShellOutputReceiver{
		
		byte[] bytes;
		@Override
		public void addOutput(byte[] arg0, int arg1, int arg2) {
			System.out.println("->");
			int n = 0;
			bytes = arg0;
			this.isRunning();
			
			System.out.println(arg1+" "+arg2);
		}

		@Override
		public void flush() {
			System.out.println(":)");
		}

		@Override
		public boolean isCancelled() {
			System.out.println("Cancelled");
			return false;
		}
		
		public void isRunning( ){
			String s = new String(bytes);
			String[] array = s.split("\n");
			
			System.out.println("Actividades");
			for( int i = 0 ; i<array.length; i++){
				System.out.println( array[i] );
			}
		}
	}
	
	UiDevice uiDevice = getUiDevice();
	
	private static void initAdbConnection() {
		String adbLocation = "adb";
	    boolean device = false;
	    boolean emulator = false;
	    String serial = null;

	    AndroidDebugBridge.init(false /* debugger support */);
	    try {
	    	System.out.println("ACA2");
		      AndroidDebugBridge bridge = AndroidDebugBridge.createBridge(
		          adbLocation, true /* forceNewBridge */);
		      // we can't just ask for the device list right away, as the internal thread getting
		      // them from ADB may not be done getting the first list.
		      // Since we don't really want getDevices() to be blocking, we wait here manually.
		      int count = 0;
		      while (bridge.hasInitialDeviceList() == false) {
		    	 System.out.println("ACA3");
		        try {
		          Thread.sleep(100);
		          count++;
		        } catch (InterruptedException e) {
		          // pass
		        }
		 
		        // let's not wait > 10 sec.
		        if (count > 100) {
		          System.err.println("Timeout getting device list!");
		          return;
		        }
		      }
		      System.out.println("ACA4");
		 	 // now get the devices
		      IDevice[] devices = bridge.getDevices();
		      IDevice monkeyDevice = null;
		      
		      if (devices.length == 0) {
		    	  printAndExit("No devices found!", true /* terminate */);
		      }
		      
		      serial = "VCGY69GMIFRWQKSG";
		      
		      for (IDevice d : devices) {
		          if (serial.equals(d.getSerialNumber())) {
		            monkeyDevice = d;
		            break;
		          }
		      }
		      
		      System.out.println("Serial:"+ monkeyDevice.getSerialNumber());
		      //monkeyDevice.
		      IShellOutputReceiver receiver = new OutShell();
		      monkeyDevice.executeShellCommand("input keyevent "+KeyEvent.KEYCODE_HOME, receiver);
		      
		      monkeyDevice.executeShellCommand("input keyevent "+KeyEvent.KEYCODE_BACK, receiver);
		      SAXParserFactory sax; 	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	}
	
	
	private static void printAndExit(String message, boolean terminate) {
	    System.out.println(message);
	    if (terminate) {
	      AndroidDebugBridge.terminate();
	    }
	    System.exit(1);
	}
}
