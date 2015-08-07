//
//  HVLAgendaEntity.swift
//  HuntVision
//
//  Created by Login Informatica on 22/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import Foundation
import CoreData

class HVLAgendaEntity: NSManagedObject {

    @NSManaged var id: NSNumber
    @NSManaged var cliente: NSNumber
    @NSManaged var dataHora: NSDate
    @NSManaged var dataHoraChegada: NSDate
    @NSManaged var dataHoraSaida: NSDate
    @NSManaged var descricao: String
    @NSManaged var usuario: NSNumber
               var usuarioOBJ: HVLUsuarioEntity?
               var clienteOBJ: HVLClienteEntity?

}
