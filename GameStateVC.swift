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
var temp = " ";
var initialize = true;

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
    
    func UpdateBoard() ->String{
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
        let reversed = String(datastring.characters.reverse())
        //temp = reversed
        print(reversed)
        //let arr = Array(arrayLiteral: reversed)[16];
        let char9 = String(reversed[reversed.startIndex.advancedBy(1)])
        let char8 = String(reversed[reversed.startIndex.advancedBy(3)])
        let char7 = String(reversed[reversed.startIndex.advancedBy(5)])
        let char6 = String(reversed[reversed.startIndex.advancedBy(7)])
        let char5 = String(reversed[reversed.startIndex.advancedBy(9)])
        let char4 = String(reversed[reversed.startIndex.advancedBy(11)])
        let char3 = String(reversed[reversed.startIndex.advancedBy(13)])
        let char2 = String(reversed[reversed.startIndex.advancedBy(15)])
        let char1 = String(reversed[reversed.startIndex.advancedBy(17)])
        
        //print(char1)
        //print(char2)
        //print(char3)
        
        if (char9 == ("X"))
        {
            sq9.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char9 == ("O")){
            sq9.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char9 == ("_")){
            
            sq9.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char8 == ("X"))
        {
            sq8.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char8 == ("O")){
            sq8.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char8 == ("_")){
            
            sq8.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char7 == ("X"))
        {
            sq7.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char7 == ("O")){
            sq7.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char7 == ("_")){
            
            sq7.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char6 == ("X"))
        {
            sq6.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char6 == ("O")){
            sq6.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char6 == ("_")){
            
            sq6.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char5 == ("X"))
        {
            sq5.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char5 == ("O")){
            sq5.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char5 == ("_")){
            
            sq5.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char4 == ("X"))
        {
            sq4.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char4 == ("O")){
            sq4.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char4 == ("_")){
            
            sq4.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char3 == ("X"))
        {
            sq3.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char3 == ("O")){
            sq3.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char3 == ("_")){
            
            sq3.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char2 == ("X"))
        {
            sq2.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char2 == ("O")){
            sq2.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char2 == ("_")){
            
            sq2.setTitle("-", forState: UIControlState.Normal)
        }
        
        if (char1 == ("X"))
        {
            sq1.setTitle("X", forState: UIControlState.Normal)
            
        } else if (char1 == ("O")){
            sq1.setTitle("O", forState: UIControlState.Normal)
            
        }else if (char1 == ("_")){
            
            sq1.setTitle("-", forState: UIControlState.Normal)
        }
        if(datastring.rangeOfString("win") != nil)
        {
            //initialize = true;
            if(datastring.rangeOfString("p1") != nil)
            {
                //display alert saying player 1 won
                let alert3 = UIAlertView();
                alert3.title = "Game Over";
                alert3.message = "Player 1 wins!";
                alert3.addButtonWithTitle("Close");
                alert3.show();
                
            }else if(datastring.rangeOfString("p2") != nil)
            {
                
                //display alert saying player 2 won
                let alert3 = UIAlertView();
                alert3.title = "Game Over";
                alert3.message = "Player 2 wins!";
                alert3.addButtonWithTitle("Close");
                alert3.show();
            }
        }
        return reversed
    }
    
    
     override func viewDidLoad() {
        super.viewDidLoad()
        
            let socket = TCPIPSocket();
            let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
            let ingame = "ingame";
            let colon = ":";
            let newline = "\n";
            
            socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
            //sleep(1);
            file.writeData((ingame + colon + uname + colon + gameid + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        let serverResponse = file.readDataToEndOfFile()
        let datastring = NSString(data:serverResponse, encoding:NSUTF8StringEncoding) as! String
        if ((datastring.rangeOfString("p2")) != nil)
        {
            initialize = false;
            workpls();
        }else{
            workpls();
        }
        // Do any additional setup after loading the view.
    }

     override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func workpls()
    {
        if(initialize == true)
        {
            UpdateBoard()
            initialize = false;
        }else{
            temp = UpdateBoard()
            while(temp.rangeOfString("win") == nil && temp == UpdateBoard())
            {
                sleep(2)
                
            }
        }
    
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
        let datastring = NSString(data:serverResponse1, encoding:NSUTF8StringEncoding) as! String

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
        /*let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let getstate = "getstate";
        //let leavematch = "leavematch";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((getstate + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        //file.writeData((leavematch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        //quitCheck = true;*/
        
        self.performSegueWithIdentifier("quit_game", sender: self)
        
        /*NSUserDefaults.standardUserDefaults().setBool(true, forKey: "isUserLoggedIn");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        self.dismissViewControllerAnimated(true, completion: nil);*/

    
    }

    @IBAction func sq1(sender: UIButton) {
        
        //UpdateBoard();
        
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq1.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
        
            //button = o
            sq1.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq1.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();
        

    }
    
    
    @IBAction func sq2(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq2.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq2.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq2.setTitle("-", forState: UIControlState.Normal)
        }*/

        workpls();
        
        
    }

    @IBAction func sq3(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq3.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq3.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq3.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }

    @IBAction func sq4(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq4.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq4.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq4.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }
    
    @IBAction func sq5(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq5.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq5.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq5.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }
    
    @IBAction func sq6(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq6.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq6.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq6.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }
    
    @IBAction func sq7(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq7.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq7.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq7.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }
    
    @IBAction func sq8(sender: UIButton) {
       // UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq8.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq8.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq8.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }
    
    @IBAction func sq9(sender: UIButton) {
        //UpdateBoard();
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
        
        /*if (whichPlayer == 1){
            
            sleep(1);
            file.writeData((gamemove as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            //button = x
            sq9.setTitle("X", forState: UIControlState.Normal)
            
        }else if(whichPlayer == 2){
            
            //button = o
            sq9.setTitle("O", forState: UIControlState.Normal)
        }else{
            sq9.setTitle("-", forState: UIControlState.Normal)
        }*/
        
        workpls();


    }
    
    
}
