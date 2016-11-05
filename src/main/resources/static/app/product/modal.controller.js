(function() {
    'use strict';

    angular.module('sampleApp').controller('ModalController', ModalController);

    ModalController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','dataService','modalService','GlobalConstants','getProductDetails'];

    function ModalController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,dataService,modalService,GlobalConstants,getProductDetails) {

        $scope.device = device;
        $scope.GlobalVariable = GlobalVariable;
        GlobalVariable.isLoginPage = false;
        $scope.selectedIndex = 0;
        $scope.isAsc = false;
        GlobalVariable.enableEdit = false;


        $rootScope.closeBootstrapAlert = function()
        {
            GlobalVariable.successAlert = false;
        };
        $scope.sortColumnData = function(index) {
            if ($scope.testGridData != null && $scope.testGridData.length > 0) {
                if (index != 0) {
                    return false;
                }
                if ($scope.isAsc) {
                    $scope.isAsc = false;
                } else {
                    $scope.isAsc = true;$scope
                }$scope
            }
        };

        $scope.navigateToProduct = function()
        {
            $state.go('productmain');
        };
        $scope.openAddModalPopup = function()
        {
            GlobalVariable.enableEdit = false;
            GlobalVariable.addHeaderName = "Add Modal";
            GlobalVariable.textName = "Modal";
            GlobalVariable.addButtonName = "Add Modal";
            var _tmPath = 'app/product/AddPopup.html';
            var _ctrlPath = 'addPopupController';
            DialogFactory.show(_tmPath, _ctrlPath, addPopupControllerCallBack);
        };
        $scope.editModal = function(modalDetails)
        {
            GlobalVariable.enableEdit = true;
            GlobalVariable.editBrandName = modalDetails.categoryName;
            GlobalVariable.editBrandDescription = modalDetails.description;
            GlobalVariable.editBrandId = modalDetails.categoryId;
            GlobalVariable.addHeaderName = "Edit Modal";
            GlobalVariable.textName = "Modal";
            GlobalVariable.addButtonName = "Edit Modal";
            var _tmPath = 'app/product/AddPopup.html';
            var _ctrlPath = 'addPopupController';
            DialogFactory.show(_tmPath, _ctrlPath, addPopupControllerCallBack);
        };
        function addPopupControllerCallBack()
        {
            GlobalVariable.addesSuccessfull = true;
            GlobalVariable.addHeaderName = "";
            GlobalVariable.textName = "";
            GlobalVariable.addButtonName = "";
        }
        $scope.deleteModal = function(modalId)
        {
            $scope.deleteModalId = modalId;
            modalService.showModal('', {
                isCancel : true
            }, "Are you Sure Want to Delete ? ", $scope.callBackDeleteAction);
        };
        $scope.callBackDeleteAction = function(isOKClicked)
        {
            if(isOKClicked)
            {
                var url=GlobalConstants.URLCONSTANTS+'deleteModal?modalId='+$scope.deleteModalId;
                var request = {};
                request=JSON.stringify(request);
                dataService.Post(url,request,deleteSuccessHandler,deleteErrorHandler,"application/json","application/text");
            }
        };
        function deleteSuccessHandler(response)
        {
            console.log(response);
            modalService.showModal('', '', response, $scope.callBackDeleteAction1);
        }
        function deleteErrorHandler(response)
        {
            console.log(response);
            modalService.showModal('', '', response, $scope.callBackDeleteAction1);
        }
        $scope.callBackDeleteAction1 = function()
        {

            //getProductDetails.getCategoryDetails($scope.getCtDetails);
        };
        $scope.getCtDetails = function(response)
        {

            GlobalVariable.getCategory= getCategory;
        };
        function render()
        {
            $scope.currentPageIndexArr = 0;
            $scope.curPageOnTotalLen = 0;
            $scope.totalLength = 0;
        }
        render();

    }

})();