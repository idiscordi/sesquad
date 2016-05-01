//
//  loading_match_VC.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/17/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

var quitCheck = false;

class loading_match_VC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func FindMatchTapped(sender: AnyObject) {
        
        
        
        //quitCheck = true;
        
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let findmatch = "findmatch";
        let colon = ":";
        let ingame = "ingame";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((findmatch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let serverResponse1 = file.readDataToEndOfFile()
        let datastring1 = NSString(data:serverResponse1, encoding:NSUTF8StringEncoding) as! String
        
        if (datastring1.rangeOfString("success") != nil){
            
            sleep(1);
            file.writeData((ingame as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            self.performSegueWithIdentifier("to_match", sender: self)
            
        }
        //let ex = "findmatch returned:success:join"+uname
        /*let serverResponse2 = file.readDataToEndOfFile()
        let datastring = NSString(data:serverResponse2, encoding:NSUTF8StringEncoding) as! String
        if (datastring.rangeOfString("success") != nil){
            
                file.writeData((ingame as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
                self.performSegueWithIdentifier("to_match", sender: self)
            
        }else{
             file.writeData((findmatch as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            self.performSegueWithIdentifier("to_match", sender: self)
            
        }*/
        
        
    
    }
    
    
    
    @IBAction func LeaveMatchTapped(sender: AnyObject) {
        
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        //let ingame = "ingame";
        let leavematch = "leavematch";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        
        
        
        file.writeData((leavematch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        quitCheck = true;
        
        
        NSUserDefaults.standardUserDefaults().setBool(true, forKey: "isUserLoggedIn");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        self.dismissViewControllerAnimated(true, completion: nil);
    }
    
    
    
    
   

}
