//
//  GameStateVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/1/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit
import Foundation

var xcoord = "0";
var ycoord = "0";
let comma = ",";
var whichPlayer = 1;

class GameStateVC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func GameStatusTapped(sender: UIButton) {
    

        
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let ingame = "ingame";
        //let leavematch = "leavematch";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((ingame + colon + uname + colon + "1" + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
       // file.writeData((leavematch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        //quitCheck = true;
        //self.performSegueWithIdentifier("goto_stats", sender: self)
        
    }
        

    
    
    
    
    @IBAction func QuitGameTapped(sender: AnyObject) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let ingame = "getstate";
        let leavematch = "leavematch";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((ingame + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        file.writeData((leavematch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        quitCheck = true;
        
        
        NSUserDefaults.standardUserDefaults().setBool(true, forKey: "to_match");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        self.dismissViewControllerAnimated(true, completion: nil);

    
    }
   
    @IBAction func sq1(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "0";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let ingame = "ingame";
        
        //socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((ingame + colon + uname + colon + "1" + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        //PROBLEMS BEGIN IN THIS BLOCK
        //***********************************************
        /*
        var serverResponse = file.readDataToEndOfFile()
        var sResp = serverResponse;
        var srArray = split(serverResponse){$0 == ":"}
        var whichP = srArray[5];
        
        if (whichP == "p1"){
            
            sleep(1);
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichP == "p2"){
        
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }*/
       //**********************************************

    }
    
    @IBAction func sq2(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "0";
        ycoord = "1";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }

    @IBAction func sq3(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "0";
        ycoord = "2";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }

    @IBAction func sq4(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "1";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }
    
    @IBAction func sq5(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "1";
        ycoord = "1";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }
    
    @IBAction func sq6(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "1";
        ycoord = "2";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }
    
    @IBAction func sq7(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "2";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }
    
    @IBAction func sq8(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "2";
        ycoord = "1";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }
    
    @IBAction func sq9(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "2";
        ycoord = "2";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + "1" + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }
    
    
}
