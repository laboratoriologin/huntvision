//
//  HVLItemLocalEntity.swift
//  HuntVision
//
//  Created by Login Informatica on 01/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import CoreData

class HVLItemLocalEntity: NSManagedObject {

    @NSManaged var id    : NSNumber
    @NSManaged var item  : NSNumber
    @NSManaged var local : NSNumber

}