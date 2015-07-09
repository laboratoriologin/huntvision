//
//  Utils.swift
//  HuntVision
//
//  Created by Login Informatica on 08/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

class Utils: NSObject {
    
    static func setUser(user: NSNumber!) {
        
        NSUserDefaults.standardUserDefaults().setObject(user, forKey: "user")
        
    }
    
    static func getUser() -> NSNumber? {
        
        return NSUserDefaults.standardUserDefaults().valueForKey("user") as? NSNumber
        
    }

}
