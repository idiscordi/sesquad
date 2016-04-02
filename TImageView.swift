//
//  TImageView.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/2/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

class TImageView: UIImageView {
    
    var player:String?
    var activated:Bool! = false
    
    func setPlayer1 (_player:String){
        self.player = _player
        
        if activated == false{
            if _player == "x"{
                self.image = UIImage(named: "x")
            }else{
                self.image = UIImage(named: "o")
            }
            activated = true

        }
    }


}
