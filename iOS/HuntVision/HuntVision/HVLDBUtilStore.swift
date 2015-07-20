//
//  HVLDBUtilStore.swift
//  HuntVision
//
//  Created by Login Informatica on 09/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

class HVLDBUtilStore: NSObject {
    
    func clean( entity: String )  {
        
        let appDelegate: AppDelegate =  UIApplication.sharedApplication().delegate as! AppDelegate
        
        let entityDescription = NSEntityDescription.entityForName(entity, inManagedObjectContext: appDelegate.managedObjectContext!)
        
        let request = NSFetchRequest()
        
        request.entity = entityDescription
        
        var error: NSError?
        
        var objects = appDelegate.managedObjectContext?.executeFetchRequest(request, error: &error)
        
        if let results = objects {
            
            var user: NSManagedObject!
            
            for var index = 0; index < results.count; ++index {
                
                user = results[index] as! NSManagedObject
                
                appDelegate.managedObjectContext?.deleteObject(user)
                
            }
            
        }
        
    }

}
