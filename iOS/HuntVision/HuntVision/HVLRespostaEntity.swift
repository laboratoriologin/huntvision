//
//  RespostaEntity.swift
//  HuntVision
//
//  Created by Login Informatica on 06/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import CoreData

class HVLRespostaEntity: NSManagedObject {

    @NSManaged var id: NSNumber
    @NSManaged var descricao: String
    @NSManaged var questionario: String

}
