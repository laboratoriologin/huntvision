//
//  HVLUsuarioEntity.swift
//  HuntVision
//
//  Created by Login Informatica on 26/06/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import CoreData

class HVLUsuarioEntity: NSManagedObject {

    @NSManaged var id      : NSString
    @NSManaged var email   : NSString
    @NSManaged var login   : NSString
    @NSManaged var senha   : NSString
    @NSManaged var celular : NSNumber
    @NSManaged var nome    : NSString

}
