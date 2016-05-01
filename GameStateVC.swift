//
//  GameStateVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/1/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit
import Foundation



var isinGame = false;
var xcoord = "0";
var ycoord = "0";
let comma = ",";
var whichPlayer = 3;
let gameid = "0";

class GameStateVC: UIViewController {
    
    @IBOutlet weak var sq1: UIButton!
    @IBOutlet weak var sq2: UIButton!
    @IBOutlet weak var sq3: UIButton!
    @IBOutlet weak var sq4: UIButton!
    @IBOutlet weak var sq5: UIButton!
    @IBOutlet weak var sq6: UIButton!
    @IBOutlet weak var sq7: UIButton!
    @IBOutlet weak var sq8: UIButton!
    @IBOutlet weak var sq9: UIButton!
    
    

    func getGameDataLoop(){
    
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        xcoord = "0";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        let ingame = "ingame";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        
        if (isinGame == true)
        //3 second delay game data loop
        {
            while (true){
                sleep(3);
                //socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
                file.writeData((getgame + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
            }
        }else if (isinGame == false){
            sleep(3);
            //socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
            file.writeData((ingame + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        }
    
    }
    
    func UpdateBoard(){
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        xcoord = "0";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        //let ingame = "ingame";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((getgame + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let serverResponse = file.readDataToEndOfFile()
        let datastring = NSString(data:serverResponse, encoding:NSUTF8StringEncoding) as! String
        
        if (datastring.rangeOfString("X:_:_:_:_:_:_:_:_") != nil)
        {
            sq1.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("O:_:_:_:_:_:_:_:_") != nil){
            sq1.setTitle("O", forState: UIControlState.Normal)
            //sender.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:X:_:_:_:_:_:_:_") != nil){
            
            sq2.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:O:_:_:_:_:_:_:_") != nil){
            
            sq2.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:X:_:_:_:_:_:_") != nil){
            
            sq3.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:O:_:_:_:_:_:_") != nil){
            
            sq3.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:X:_:_:_:_:_") != nil){
            
            sq4.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:O:_:_:_:_:_") != nil){
            
            sq4.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:X:_:_:_:_") != nil){
            
            sq5.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:O:_:_:_:_") != nil){
            
            sq5.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:X:_:_:_") != nil){
            
            sq6.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:O:_:_:_") != nil){
            
            sq6.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:_:X:_:_") != nil){
            
            sq7.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:_:O:_:_") != nil){
            
            sq7.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:_:_:X:_") != nil){
            
            sq8.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:_:_:O:_") != nil){
            
            sq8.setTitle("O", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:_:_:_:X") != nil){
            
            sq9.setTitle("X", forState: UIControlState.Normal)
            
        }else if (datastring.rangeOfString("_:_:_:_:_:_:_:_:O") != nil){
            
            sq9.setTitle("X", forState: UIControlState.Normal)
            
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
       /* while(true)
        {
            let socket = TCPIPSocket();
            let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
            let getGameData = "getGameData";
            let colon = ":";
            let newline = "\n";
            
            socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
            sleep(1);
            file.writeData((getGameData + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        }*/

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
        file.writeData((ingame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let serverResponse1 = file.readDataToEndOfFile()
        var datastring = NSString(data:serverResponse1, encoding:NSUTF8StringEncoding) as! String

        if (datastring.rangeOfString("success") != nil)
        {
        isinGame = true;
        }else if (datastring.rangeOfString("failed") != nil){
        isinGame = false;
        }
        
       getGameDataLoop();
        if(datastring.rangeOfString("p1") != nil)
        {
            whichPlayer = 1;
        }else if (datastring.rangeOfString("p1") != nil){
            whichPlayer = 2;
        }
        
    }
        

    
    
    
    
    @IBAction func QuitGameTapped(sender: AnyObject) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let getstate = "getstate";
        //let leavematch = "leavematch";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((getstate + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        //file.writeData((leavematch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
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
        
        
        let getgame = "getGameData";
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        
        
        //safe gamemove block
       file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        
       // let serverResponse1 = file.readDataToEndOfFile()
       //var datastring = NSString(data:serverResponse1, encoding:NSUTF8StringEncoding) as! String

        //print(last)
        //print(datastring)
        //print(sR)
        //if datastring.rangeOfString("X:_:_:_:_:_:_:_:_")
        
        
        //block with server response formatted
        /*for index in datastring.characters
        {
            //get game data
            file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //search for successful move
            if ((datastring.rangeOfString("X:_:_:_:_:_:_:_:_")) != nil){
                
                sleep(1);
                file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
                //button = x
                sender.setTitle("X", forState: UIControlState.Normal)
                
            }else if((datastring.rangeOfString("O:_:_:_:_:_:_:_:_")){
                
                //button = o
                sender.setTitle("O", forState: UIControlState.Normal)
            }else{
            sender.setTitle(" ", forState: UIControlState.Normal)
            }
        }*/
        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
        
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()
        

    }
    
    @IBAction func sq2(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "0";
        ycoord = "1";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]

        
        
        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }

        UpdateBoard()
        
        
    }

    @IBAction func sq3(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "0";
        ycoord = "2";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]

        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }

    @IBAction func sq4(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "1";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]

        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }
    
    @IBAction func sq5(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "1";
        ycoord = "1";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]

        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }
    
    @IBAction func sq6(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "1";
        ycoord = "2";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]

        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }
    
    @IBAction func sq7(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "2";
        ycoord = "0";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]
        
        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }
    
    @IBAction func sq8(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "2";
        ycoord = "1";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)

        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]
        
        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }
    
    @IBAction func sq9(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let gamemove = "gamemove";
        xcoord = "2";
        ycoord = "2";
        let colon = ":";
        let newline = "\n";
        let getgame = "getGameData";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((gamemove + colon + uname + colon + gameid + colon + xcoord + comma + ycoord + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        file.writeData((getgame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        //let serverResponse = file.readDataToEndOfFile()
        //let last = Array(arrayLiteral: serverResponse)[0]
        
        //New stuff
        
        if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sender.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sender.setTitle("O", forState: UIControlState.Normal)
        }else{
            sender.setTitle("-", forState: UIControlState.Normal)
        }
        
        UpdateBoard()


    }
    
    
}
