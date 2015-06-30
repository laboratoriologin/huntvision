//
//  HVLClienteEntity.swift
//  
//
//  Created by Login Informatica on 29/06/15.
//
//

import Foundation
import CoreData

class HVLClienteEntity: NSManagedObject {

    @NSManaged var id: NSNumber
    @NSManaged var nome: String
    @NSManaged var email: String
    @NSManaged var celular: String
    @NSManaged var cnpj: String
    @NSManaged var endereco: String
    @NSManaged var cidade: String
    @NSManaged var bairro: String
    @NSManaged var estado: String
    @NSManaged var cep: String
    @NSManaged var pais: String

}
