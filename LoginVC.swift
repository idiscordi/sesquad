//
//  LoginVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 3/16/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

class LoginVC: UIViewController {
    
    @IBOutlet weak var txtUsername: UITextField!
    @IBOutlet weak var txtPassword: UITextField!


    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func signinTapped(sender: AnyObject) {
        let userUsername = txtUsername.text;
        let userPassword = txtPassword.text;
        
        //if(userUsername!.isEmpty || userPassword!.isEmpty){return;}
        
        
        
        //read data
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData(("poop\n" as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        //let myURL = NSURL(string: "uaf132992.ddns.uark.edu");
        //let request = NSMutableURLRequest(URL: myURL!);
        
        //request.HTTPMethod = "POST";
        
        
        //let postString = "username=\(userUsername)&password=\(userPassword)";
        //request.HTTPBody = postString.dataUsingEncoding(NSUTF8StringEncoding)
        
        let serverResponse = file.readDataToEndOfFile()
        
        print(NSString(data: serverResponse, encoding: NSUTF8StringEncoding)!)

        
        //check to see if info matches
        
        
        //login successful
        NSUserDefaults.standardUserDefaults().setBool(true, forKey: "isUserLoggedIn");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        self.dismissViewControllerAnimated(true, completion: nil);
        
    }
    
   

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
