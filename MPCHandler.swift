//
//  MPCHandler.swift
//  Mobile Client
//
//  Created by Joel Garay on 4/2/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit
import MultipeerConnectivity

class MPCHandler: NSObject, MCSessionDelegate {
    
    var peerID:MCPeerID!
    var session:MCSession!
    var browser:MCBrowserViewController!
    var advertiser:MCAdvertiserAssistant? = nil
    
    func setupPeer_DisplayName (displayName:String){
        peerID = MCPeerID(displayName: displayName)
    }
    
    func setup_Session(){
        session = MCSession(peer: peerID)
        session.delegate = self
    }
    
    func setup_Browser(){
        browser = MCBrowserViewController(serviceType: "my_game", session: session)
    }
    
    func advertise_Self(advertise:Bool){
        if advertise{
            advertiser = MCAdvertiserAssistant(serviceType: "my_game", discoveryInfo: nil, session: session)
            advertiser!.start()
        }else{
            advertiser!.stop()
            advertiser = nil
        }
    }
    
    func session(session: MCSession, peer peerID: MCPeerID, didChangeState state: MCSessionState) {
        
    }
    
    func session(session: MCSession, didReceiveData data: NSData, fromPeer peerID: MCPeerID) {
        
    }
    
    func session(session: MCSession, didStartReceivingResourceWithName resourceName: String, fromPeer peerID: MCPeerID, withProgress progress: NSProgress) {
        
    }
    
    func session(session: MCSession, didFinishReceivingResourceWithName resourceName: String, fromPeer peerID: MCPeerID, atURL localURL: NSURL, withError error: NSError?) {
        
    }
    
    func session(session: MCSession, didReceiveStream stream: NSInputStream, withName streamName: String, fromPeer peerID: MCPeerID) {
        
    }
    

}
