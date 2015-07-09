//
//  HVLLocalEntity.swift
//  HuntVision
//
//  Created by Login Informatica on 30/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import CoreData

class HVLLocalEntity: NSManagedObject {

    @NSManaged var id      : NSNumber
    @NSManaged var nome    : String
    @NSManaged var cliente : NSNumber

}
