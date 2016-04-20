//
//  StatVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/19/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

class StatVC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func StatsTapped(sender: UIButton) {
        
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let getstats = "getdatabyuser";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((getstats + colon + uname + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
    }

    
}
