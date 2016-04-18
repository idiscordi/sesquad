//
//  ViewController.swift
//  Mobile Client
//
//  Created by Joel Garay on 3/16/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var UsernameLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        //Dispose of any resources that can be recreated
    }
    
    override func viewDidAppear(animated: Bool) {
        let isUserLoggedIn = NSUserDefaults.standardUserDefaults().boolForKey("isUserLoggedIn");
        
        if(!isUserLoggedIn){
            self.performSegueWithIdentifier("goto_login", sender: self)
        }
}
    
    @IBAction func LogoutTapped(sender: UIButton) {
        NSUserDefaults.standardUserDefaults().setBool(false, forKey: "isUserLoggedIn");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        
        
        self.performSegueWithIdentifier("goto_login", sender: self)
    }
    
    @IBAction func PlayGameTapped(sender: UIButton) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let findmatch = "findmatch";
        let colon = ":";
        let ingame = "ingame";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((findmatch + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let serverResponse = file.readDataToEndOfFile()
        let first = Array(arrayLiteral: serverResponse)[0]
        
        if (first == "f"){
            
            sleep(1);
            file.writeData((findmatch as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            
        }
        let ex = "findmatch returned:success:join"+uname
        let serverResponse2 = file.readDataToEndOfFile()
        let fst = Array(arrayLiteral: serverResponse2)[0]
        if (fst == ex){
            
            while(1 > 0)
            {
                sleep(1);
                file.writeData((ingame as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            }
            
        }
        self.performSegueWithIdentifier("goto_GameState", sender: self)
    }


}

