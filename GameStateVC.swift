//
//  GameStateVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/1/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit
import Foundation

class GameStateVC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func FindMatchTapped(sender: AnyObject) {
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let findmatch = "findmatch:";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((findmatch + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let serverResponse = file.readDataToEndOfFile()
        let first = Array(arrayLiteral: serverResponse)[0]
        
        if (first == "f"){
            
            sleep(1);
            file.writeData((findmatch as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
            
        }
        
    }
    
    
    @IBAction func LogoutTapped(sender: AnyObject) {
        
        
        
    }
    

   

}
