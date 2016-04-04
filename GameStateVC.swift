//
//  GameStateVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/1/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

class GameStateVC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func ConnectTapped(sender: UIBarButtonItem) {
        
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((" Connect Request\n" as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        let serverResponse = file.readDataToEndOfFile()
        
        
        print(NSString(data: serverResponse, encoding: NSUTF8StringEncoding)!)

    }

    @IBAction func QuitGameTapped(sender: UIButton) {
        NSUserDefaults.standardUserDefaults().setBool(true, forKey: "isUserLoggedIn");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        self.dismissViewControllerAnimated(true, completion: nil);
    }
   

}
